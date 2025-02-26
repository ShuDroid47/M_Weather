package com.example.m_weather.helpers;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class CheckNetworkConnection extends LiveData<Boolean> {
    private final ConnectivityManager cManager;
    private final NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback(){
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            postValue(true);
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            postValue(false);
        }
    };

    public CheckNetworkConnection(@NonNull Application application) {
        this.cManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    protected void onActive() {
        super.onActive();
        cManager.registerDefaultNetworkCallback(networkCallback);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        cManager.unregisterNetworkCallback(networkCallback);
    }
}
