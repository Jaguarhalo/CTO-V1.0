package com.example.corp_01agmp.ctov10.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Usuario 1 on 11/05/2016.
 */
public class Pagos {

    private Conexion conexion;
    private SQLiteDatabase db;

    /*              Variables de la Clase Saldo           */
    private static final String TABLE_NAME = "tPagos";
    private static final String CN_ID = "id";
    private static final String CN_CONTRATO = "contrato";
    private static final String CN_PAGARE = "pagare";
    private static final String CN_FPAGO = "fPago";
    private static final String CN_MONTO = "montoPagado";



    /*              Creacion de la tabla Usuario           */

    public static String CREA_TABLE_PAGOS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            CN_ID + " INTEGER NOT NULL, " +
            CN_CONTRATO + " TEXT NOT NULL, " +
            CN_PAGARE + " TEXT NOT NULL, " +
            CN_FPAGO + " TEXT NOT NULL, " +
            CN_MONTO + " DECIMAL(12,5) NOT NULL);";


    /*              Funciones de la Clase Usuario           */

    public Pagos(Context ctx) {
        conexion = new Conexion(ctx);
        db = conexion.getWritableDatabase();
    }


    ContentValues generaValoresPagos(Integer id,String contrato, String pagare, String fPago, double monto){

        ContentValues valores = new ContentValues();
        valores.put(CN_ID,id);
        valores.put(CN_CONTRATO,contrato);
        valores.put(CN_PAGARE,pagare);
        valores.put(CN_FPAGO,fPago);
        valores.put(CN_MONTO,monto);

        return  valores;
    }

    public void cerrar(){
        db.close();
    }

    public Boolean comprobarPagos() {
        Boolean valido = true;

        Cursor resultSet = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        if(resultSet.getCount()>0){valido=true;}else{valido=false;}

        return valido;
    }

    public Cursor cargaPagosContrato(int id) {
        //String[] args = new String[]{cliente};
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CN_ID + "=" + id +" Order By " + CN_FPAGO + " desc",null);
    }

    public void insertarPagos(Integer id,String contrato, String pagare, String fPago, double monto) {
        db.insert(TABLE_NAME,null,generaValoresPagos(id,contrato, pagare, fPago, monto));
        Log.d("Inserto: ",id + ".- " + pagare);
    }

    public void borraTodoPagos() {
        db.execSQL("DELETE FROM " + TABLE_NAME + ";");
    }
}

