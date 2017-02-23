package com.example.corp_01agmp.ctov10.Recursos;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Usuario 1 on 12/05/2016.
 */
public class ValidaInternet extends Activity {


    public Boolean conectadoWifi(Context ctx){

        Boolean conectado;

        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);

        NetworkInfo ni = cm.getActiveNetworkInfo();

        if(ni != null && ni.isConnectedOrConnecting()) {
            conectado = true;
        }else{
            conectado = false;
        }

        return conectado;
    }
}
