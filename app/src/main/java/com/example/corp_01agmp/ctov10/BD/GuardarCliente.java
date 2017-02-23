package com.example.corp_01agmp.ctov10.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Usuario 1 on 11/05/2016.
 */
public class GuardarCliente {
    private Conexion conexion;
    private SQLiteDatabase db;

    /*              Variables de la Clase Usuario           */
    private static final String TABLE_NAME = "tGuardarCliente";
    private static final String CN_ID = "idCliente";
    private static final String CN_NOMBRE = "nombreCliente";



    /*              Creacion de la tabla Usuario           */

    public static String CREA_TABLE_BCLIENTE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            CN_NOMBRE + " TEXT NOT NULL);";


    /*              Funciones y constructor             */

    public GuardarCliente(Context ctx) {
        conexion = new Conexion(ctx);
        db = conexion.getWritableDatabase();
    }

    public Boolean comprobarGuardado() {
        Boolean valido = true;

        Cursor resultSet = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        if(resultSet.getCount()>0){valido=true;}else{valido=false;}

        return valido;
    }
    ContentValues generaValoresBCliente(String nombre){
        ContentValues valores = new ContentValues();
        valores.put(CN_NOMBRE,nombre);
        return  valores;
    }

    public void insertarBCliente(String nombre) {
        Log.d("",db.insert(TABLE_NAME,null,generaValoresBCliente(nombre))+"");
    }

    public Cursor cargaTodo(){
        Cursor resultSet = db.rawQuery("SELECT * FROM " + TABLE_NAME ,null);
        return resultSet;
    }

    public void borraTodoBCliente() {
        db.execSQL("DELETE FROM " + TABLE_NAME + ";");
    }
}
