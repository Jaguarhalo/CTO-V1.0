package com.example.corp_01agmp.ctov10.Recursos;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.corp_01agmp.ctov10.R;

/**
 * Created by kratos on 24/01/2017.
 */

public class Mensajes {

    public Mensajes mostrarMensaje(View v, Context ctx, String mensaje){
        Snackbar sn = Snackbar.make(v,mensaje, Snackbar.LENGTH_LONG);
        View snb = sn.getView();
        snb.setBackgroundColor(ctx.getResources().getColor(R.color.colorPrimaryDark));
        sn.show();
        return null;
    }

}
