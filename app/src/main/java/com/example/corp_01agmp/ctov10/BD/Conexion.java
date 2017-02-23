package com.example.corp_01agmp.ctov10.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Usuario 1 on 11/05/2016.
 */
public class Conexion extends SQLiteOpenHelper {
    private static final String DB_NAME = "DBCTO";
    private static final Integer DB_SCHEME = 1;

    public Conexion(Context context) {
        super(context, DB_NAME, null, DB_SCHEME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioBD.CREA_TABLE_USUARIO);
        db.execSQL(GuardarCliente.CREA_TABLE_BCLIENTE);
        db.execSQL(Saldo.CREA_TABLE_SALDO);
        db.execSQL(Pagos.CREA_TABLE_PAGOS);
        db.execSQL(Socio.CREA_TABLE_SOCIO);
        db.execSQL(Vencimientos.CREA_TABLE_VENCIMIENTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.delete("DELETE FROM tUsuario",null,null);
        db.delete("DELETE FROM tSaldo",null,null);
        db.delete("DELETE FROM tPagos",null,null);
        db.delete("DELETE FROM tSocio",null,null);
        db.delete("DELETE FROM tVencimientos",null,null);
        onCreate(db);
    }
}
