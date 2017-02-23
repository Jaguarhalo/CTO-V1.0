package com.example.corp_01agmp.ctov10.Recursos;

import android.content.Context;
import android.database.Cursor;

import com.example.corp_01agmp.ctov10.BD.GuardarCliente;
import com.example.corp_01agmp.ctov10.BD.Socio;

/**
 * Created by corp_01agmp on 09/02/2017.
 */

public class Cursores {

    public String cargaclienteguardado(Context ctx) {
        String cliente = "";
        Cursor c = new GuardarCliente(ctx).cargaTodo();
        if(c.getCount() > 0){
            if(c.moveToFirst()){
                do{
                    cliente = c.getString(0).toString();
                }while (c.moveToNext());
            }
        }
        return cliente;
    }

    public Integer cargaidclienteguardado(Context ctx,String nombre) {
        Integer idcliente = 0;
        Cursor c = new Socio(ctx).buscaSocioId(nombre);
        if(c.getCount() > 0){
            if(c.moveToFirst()){
                do{
                    idcliente = c.getInt(0);
                }while (c.moveToNext());
            }
        }
        return idcliente;
    }
}
