package com.example.corp_01agmp.ctov10.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Usuario 1 on 11/05/2016.
 */
public class Vencimientos {

    private Conexion conexion;
    private SQLiteDatabase db;

    private static final String TABLE_NAME = "tVencimientos";
    private static final String CN_ID = "id";
    private static final String CN_NOMBRE = "nombre";
    private static final String CN_PAGARE = "pagare";
    private static final String CN_FVENCIMIENTO = "fecha";
    private static final String CN_MONTO = "monto";
    private static final String CN_CAPITAL = "capital";
    private static final String CN_INTERES = "interes";
    private static final String CN_MORATORIOS = "moratorios";
    private static final String CN_FCORTE = "fcorte";

    public static String CREA_TABLE_VENCIMIENTOS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            CN_ID + " INTEGER NOT NULL, " +
            CN_NOMBRE + " TEXT NOT NULL, " +
            CN_PAGARE + " TEXT NOT NULL, " +
            CN_FVENCIMIENTO + " TEXT NOT NULL, " +
            CN_MONTO + " DECIMAL(10,2) NOT NULL, " +
            CN_CAPITAL + " DECIMAL(10,2) NOT NULL, " +
            CN_INTERES + " DECIMAL(10,2) NOT NULL, " +
            CN_MORATORIOS + " DECIMAL(10,2) NOT NULL, " +
            CN_FCORTE + " TEXT NOT NULL);";

    public Vencimientos(Context ctx) {
        conexion = new Conexion(ctx);
        db = conexion.getWritableDatabase();
    }

    ContentValues generaValoresPagos(Integer id,String nombre,String pagare, String fvencimiento, Double monto, Double capital, double interes,double moratorios,
                                     String fcorte){

        ContentValues valores = new ContentValues();
        valores.put(CN_ID,id);
        valores.put(CN_NOMBRE,nombre);
        valores.put(CN_PAGARE,pagare);
        valores.put(CN_FVENCIMIENTO,fvencimiento);
        valores.put(CN_MONTO,monto);
        valores.put(CN_CAPITAL,capital);
        valores.put(CN_INTERES,interes);
        valores.put(CN_MORATORIOS,moratorios);
        valores.put(CN_FCORTE,fcorte);
        return  valores;
    }

    public Boolean comprobarVencimientos() {
        Boolean valido = true;
        Cursor resultSet = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
        if(resultSet.getCount()>0){valido=true;}else{valido=false;}
        return valido;
    }

    public void insertarVencimientos(Integer id,String nombre,String pagare, String fvencimiento, Double monto, Double capital, double interes,double moratorios,
                                     String fcorte) {
        db.insert(TABLE_NAME,null,generaValoresPagos(id,nombre,pagare, fvencimiento, monto,capital,interes,moratorios,fcorte));
        Log.d("Inserto: ",id + ".- " + nombre);
    }

    public Cursor cargaVencimientos() {
        //String[] args = new String[]{cliente};
        return db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
    }

    public void borraTodoVencimientos() {
        //String[] args = new String[]{cliente};
        db.rawQuery("DELETE FROM " + TABLE_NAME,null);
    }

}
