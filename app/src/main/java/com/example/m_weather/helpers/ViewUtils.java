package com.example.m_weather.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
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

    public void ShowErrorToast(String msge) {
        Toast.makeText(cx, msge, Toast.LENGTH_SHORT).show();
    }
}
