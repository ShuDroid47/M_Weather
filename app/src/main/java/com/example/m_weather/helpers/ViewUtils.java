package com.example.m_weather.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.example.m_weather.R;
import com.example.m_weather.databinding.ActivityMainBinding;
import com.example.m_weather.datamodels.Main;
import com.example.m_weather.datamodels.WeatherResponse;

public class ViewUtils {
    Context cx;
    ActivityMainBinding mLayout;
    ProgressDialog pd = null;
    AlertDialog dialog = null;

    public ViewUtils(Context cx) {
        this.cx = cx;
        mLayout = DataBindingUtil.setContentView((Activity) cx, R.layout.activity_main);
    }

    public void showProgress(){
        pd = new ProgressDialog(cx);
        pd.setMessage("Please wait....");
        pd.show();
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
    }

    public void dismissProgress(){
        if(pd!=null){
            if(pd.isShowing())
                pd.dismiss();
        }
    }

    public void ShowNoNetworkDialog(){
        AlertDialog.Builder adb  = new AlertDialog.Builder(cx, androidx.appcompat.R.style.Base_Theme_AppCompat_Dialog);
        View dialogView = LayoutInflater.from(cx).inflate(R.layout.dialog_no_wifi,null);
        adb.setView(dialogView);
        dialog = adb.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissNetworkDialog(){
        if(dialog!=null)
        {
            if(dialog.isShowing()){
                dialog.dismiss();
            }
        }
        else{
            Toast.makeText(cx,"No Dialog Found",Toast.LENGTH_SHORT).show();
        }
    }


    public void ShowErrorToast(String msge) {
        Toast.makeText(cx, msge, Toast.LENGTH_SHORT).show();
    }
}
