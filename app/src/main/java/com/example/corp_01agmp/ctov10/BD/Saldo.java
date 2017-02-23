package com.example.corp_01agmp.ctov10.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Usuario 1 on 11/05/2016.
 */
public class Saldo {
    private Conexion conexion;
    private SQLiteDatabase db;

    /*              Variables de la Clase Saldo           */
    private static final String TABLE_NAME = "tSaldo";
    private static final String CN_IDCLIENTE = "idCliente";
    private static final String CN_CONTRATO = "contrato";
    private static final String CN_PAGARE = "pagare";
    private static final String CN_FDESEMBOLSO = "fDesembolso";
    private static final String CN_FVENCIMIENTO = "fVencimiento";
    private static final String CN_TINTERES = "tInteres";
    private static final String CN_TMORATORIA = "tMoratoria";
    private static final String CN_DIASMORA = "diasMora";
    private static final String CN_MONTO = "monto";
    private static final String CN_CAPITAL = "capital";
    private static final String CN_INTERES = "interes";
    private static final String CN_MORATORIOS = "moratorios";
    private static final String CN_FCORTE = "fCorte";



    /*              Creacion de la tabla Usuario           */

    public static String CREA_TABLE_SALDO = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            CN_IDCLIENTE + " INTEGER NOT NULL, " +
            CN_CONTRATO + " TEXT NOT NULL, " +
            CN_PAGARE + " TEXT NOT NULL, " +
            CN_FDESEMBOLSO + " TEXT NOT NULL, " +
            CN_FVENCIMIENTO + " TEXT NOT NULL, " +
            CN_TINTERES + " DECIMAL(12,5) NOT NULL, " +
            CN_TMORATORIA + " DECIMAL(12,5) NOT NULL, " +
            CN_DIASMORA + " INTEGER NOT NULL, " +
            CN_MONTO + " DECIMAL(12,5) NOT NULL, " +
            CN_CAPITAL + " DECIMAL(12,5) NOT NULL, " +
            CN_INTERES + " DECIMAL(12,5) NOT NULL, " +
            CN_MORATORIOS + " DECIMAL(12,5) NOT NULL, " +
            CN_FCORTE + " TEXT NOT NULL);";



    /*              Funciones de la Clase Usuario           */

    public Saldo(Context ctx) {
        conexion = new Conexion(ctx);
        db = conexion.getWritableDatabase();
    }

    public void cerrar(){
        db.close();
    }

    ContentValues generaValoresSaldo(String idCliente, String contrato, String pagare, String fDesembolso,
                                     String fVencimiento, Integer tInteres, Integer tMoratoria, Integer diasMora, double monto, double capital, Double interes,
                                     Double moratorios, String fCorte){

        ContentValues valores = new ContentValues();

        valores.put(CN_IDCLIENTE,idCliente);
        valores.put(CN_CONTRATO,contrato);
        valores.put(CN_PAGARE,pagare);
        valores.put(CN_FDESEMBOLSO,fDesembolso);
        valores.put(CN_FVENCIMIENTO,fVencimiento);
        valores.put(CN_TINTERES,tInteres);
        valores.put(CN_TMORATORIA,tMoratoria);
        valores.put(CN_DIASMORA,diasMora);
        valores.put(CN_MONTO,monto);
        valores.put(CN_CAPITAL,capital);
        valores.put(CN_INTERES,interes);
        valores.put(CN_MORATORIOS,moratorios);
        valores.put(CN_FCORTE,fCorte);
        return  valores;
    }

    public Boolean comprobarRegistros() {

        Boolean valido;

        Cursor resultSet = db.rawQuery("SELECT * FROM " + TABLE_NAME ,null);
        if(resultSet.getCount()>0){valido=true;}else{valido=false;}
        return valido;
    }

    public void insertarSaldo(String idCliente, String contrato, String pagare, String fDesembolso,
                              String fVencimiento, Integer tInteres, Integer tMoratoria, Integer diasMora, Double monto, Double capital, Double interes,
                              Double moratorios, String fCorte) {
        db.insert(TABLE_NAME,null,generaValoresSaldo(idCliente, contrato, pagare,fDesembolso, fVencimiento, tInteres,
                tMoratoria, diasMora, monto, capital, interes, moratorios, fCorte));
        Log.d("Inserto: ",idCliente + ".- " + pagare);
    }
    public Cursor cargaCreditos(Integer idcliente) {
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CN_IDCLIENTE +  "='" + idcliente + "'",null);
    }
    /*
    public Cursor cargaCliente(String cliente) {
        //String[] args = new String[]{cliente};
        Log.d("SQL: ","SELECT DISTINCT(" + CN_CLIENTE + ") FROM " + TABLE_NAME + " WHERE " + CN_CLIENTE + " like '%" + cliente + "%' ");
        return db.rawQuery("SELECT DISTINCT(" + CN_CLIENTE + ") FROM " + TABLE_NAME + " WHERE " + CN_CLIENTE + " like '%" + cliente + "%' ",null);
    }

    public Cursor cargaCreditosCliente(String cliente) {
        //String[] args = new String[]{cliente};
        return db.rawQuery("SELECT " + CN_PAGARE + "," + CN_MONTO + " FROM " + TABLE_NAME + " WHERE " + CN_CLIENTE + "='" + cliente +"'",null);
    }



    public Cursor cargaGrupo(String grupo) {
        //String[] args = new String[]{cliente};
        Log.d("SQL: ","SELECT DISTINCT(" + CN_GRUPO + ") FROM " + TABLE_NAME + " WHERE " + CN_GRUPO + " like '%" + grupo + "%' ");
        return db.rawQuery("SELECT DISTINCT(" + CN_GRUPO + ") FROM " + TABLE_NAME + " WHERE " + CN_GRUPO + " like '%" + grupo + "%' ",null);
    }

    public Cursor cargaClientesGrupo(String grupo) {
        //String[] args = new String[]{cliente};
        return db.rawQuery("SELECT DISTINCT(" + CN_CLIENTE + ") FROM " + TABLE_NAME + " WHERE " + CN_GRUPO + " = '" + grupo + "' ",null);
    }

    public Cursor cargaTodo(){
        Cursor resultSet = db.rawQuery("SELECT * FROM " + TABLE_NAME ,null);
        return resultSet;
    }*/

    public void borraTodoSaldo() {
        db.execSQL("DELETE FROM " + TABLE_NAME + ";");
    }
}

