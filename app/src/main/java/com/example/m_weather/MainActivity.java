package com.example.m_weather;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.m_weather.databinding.ActivityMainBinding;
import com.example.m_weather.databinding.DialogSearchBinding;
import com.example.m_weather.datamodels.WeatherDataModel;
import com.example.m_weather.datamodels.WeatherResponse;
import com.example.m_weather.helpers.KelvinConverter;
import com.example.m_weather.helpers.ViewUtils;
import com.example.m_weather.helpers.WeatherApiListener;
import com.example.m_weather.repos.ApiRepos;

import java.security.Permission;

public class MainActivity extends AppCompatActivity implements WeatherApiListener {

    ActivityMainBinding mLayout;
    boolean isCelsius = true;
    MainViewModel model;
    WeatherApiListener wListener;
    ViewUtils utils;
    WeatherResponse weatherData;
    WeatherDataModel tData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wListener = this;
        model = new MainViewModel();
        utils = new ViewUtils(this);
        mLayout = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mLayout.setModel(model);
        model.listener = this;
        model.ApiKey = getString(R.string.api_key);

        if(CheckSelfPermission()) {
            ProcessData();
            mLayout.menuIconImageView.setOnClickListener(view -> {
                SetMenu();
            });
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},2);
        }
    }

    //TO Popup Menu Change the Metric Values.
    private void SetMenu() {
        PopupMenu menu = new PopupMenu(this,mLayout.menuIconImageView);
        menu.inflate(R.menu.metrics);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_celsius:
                        if(!isCelsius){
                            isCelsius = true;
                            SetCelsiusData();
                            break;
                        }
                    case R.id.menu_fahrenheit:
                        if(isCelsius){
                            isCelsius = false;
                            SetFahrenheitData();
                            break;
                        }
                }
                return true;
            }
        });
        menu.show();
    }

    //Get Data from the Server
    private void ProcessData() {
        if (GetLocationHistory().isEmpty()) {
            ShowSearchDialog();
        } else {
            model.cityName = GetLocationHistory();
            model.FuncGetData();
        }
    }

    //ON Permission engage.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            ProcessData();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    private boolean CheckSelfPermission() {
        if((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)){
            return false;
        }
        else
            return true;
    }

//Dialog popups for the new Application Install... Due to no location history.
    AlertDialog ad;
    public void ShowSearchDialog(){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_search,null);
        adb.setView(v);

        ad = adb.create();
        ad.setCanceledOnTouchOutside(false);
        ad.setCancelable(false);
        ad.show();
        v.findViewById(R.id.btn_dialog_get).setOnClickListener(view -> {
            String cName = ((EditText)v.findViewById(R.id.et_dialog_search)).getText().toString();
            if(cName.isEmpty())
                onFailure("Enter City Name");
            else {
                ad.dismiss();
                model.cityName = cName;
                model.FuncGetData();
                SaveLocationHistory(cName);
            }
        });
    }

    //saving location/City Name as Search History into the sharedpreferences.
    private void SaveLocationHistory(String cName) {
        SharedPreferences sp = getSharedPreferences("Location History",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Location",cName);
        editor.apply();
    }

    //checking for the search History.
    private String GetLocationHistory(){
        SharedPreferences sp = getSharedPreferences("Location History",MODE_PRIVATE);
        return sp.getString("Location","");
    }

    @Override
    public void onStarted() {
        //Invoke the ProgressDialog
        utils.showProgress();
    }

    @Override
    public void OnSuccess(WeatherResponse weatherData) {
//After Getting the Result Assigning the Data the Models to send it to the view
        this.weatherData = weatherData;
        tData = new WeatherDataModel();
        tData.setHumidity(weatherData.getMain().getHumidity().toString()+"%");
        tData.setPressure(weatherData.getMain().getPressure().toString()+" Pa");
        tData.setVisibility(String.format("%.2f",(double) weatherData.getVisibility()/1000)+"Km");
        tData.setWindspeed(weatherData.getWind().getSpeed().toString()+"m/s");
        if(isCelsius){
            SetCelsiusData();
        }
        else{
            SetFahrenheitData();
        }

        //Setting data for dataBinding
        mLayout.setModelData(weatherData);
        model.loadImage(weatherData.getWeather().get(0).getIcon());//Getting Image After the Data

        if(ad!=null)
            if(ad.isShowing()){
                ad.dismiss();
            }
    }

    //Data Converts to Celsius Metric
    private void SetCelsiusData(){
        tData.setMetric(getString(R.string.celsius));

        tData.setMaxbymin(new KelvinConverter().getCelsius(weatherData.getMain().getTempMax())+"\u00B0/"+new KelvinConverter().getCelsius(weatherData.getMain().getTempMin())+"\u00B0");
        tData.setTemp(new KelvinConverter().getCelsius(weatherData.getMain().getTemp()));
        tData.setFeellike("Feels like "+new KelvinConverter().getCelsius(weatherData.getMain().getFeelsLike())+"\u00B0");
        mLayout.setWData(tData);
    }

    //Data converts to Fahrenheit metric
    private void SetFahrenheitData(){
        tData.setMetric(getString(R.string.fahrenheit));
        tData.setMaxbymin(new KelvinConverter().getFahrenheit(weatherData.getMain().getTempMax())+"\u00B0"+"/"+new KelvinConverter().getFahrenheit(weatherData.getMain().getTempMin())+"\u00B0");
        tData.setTemp(new KelvinConverter().getFahrenheit(weatherData.getMain().getTemp()));
        tData.setFeellike("Feels like "+new KelvinConverter().getFahrenheit(weatherData.getMain().getFeelsLike())+"\u00B0");
        mLayout.setWData(tData);
    }

    @Override
    public void SetWeatherIcon(Bitmap map) {
        //Setting the Weather Icon after getting it from the Server
        mLayout.weatherIconImageView.setImageBitmap(map);
        onEnd();
    }

    @Override
    public void onEnd() {
        utils.dismissProgress();
    }

    @Override
    public void onFailure(String msge) {
        utils.dismissProgress();
        utils.ShowErrorToast(msge);
    }
}