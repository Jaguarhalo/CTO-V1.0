package com.example.corp_01agmp.ctov10.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by kratos on 24/01/2017.
 */

public class UsuarioBD {
    private Conexion conexion;
    private SQLiteDatabase db;

    /*              Variables de la Clase Usuario           */
    private static final String TABLE_NAME = "tUsuario";
    private static final String CN_IDUSUARIO = "idUsuario";
    private static final String CN_USUARIO_NOMBRE = "nombreUsuario";
    private static final String CN_USUARIO_CONTRASENA = "contrasenaUsuario";
    private static final String CN_USUARIO_STATUS = "usuarioSTATUS";
    private static final String CN_USUARIO_OFICINA = "usuarioOficina";


    /*              Creacion de la tabla Usuario           */

    public static String CREA_TABLE_USUARIO = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            CN_IDUSUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CN_USUARIO_NOMBRE + " TEXT NOT NULL," +
            CN_USUARIO_CONTRASENA + " TEXT NOT NULL," +
            CN_USUARIO_STATUS + " INTEGER NOT NULL," +
            CN_USUARIO_OFICINA + " INTEGER NOT NULL);";


    /*              Funciones y constructor             */

    public UsuarioBD(Context ctx) {
        conexion = new Conexion(ctx);
        db = conexion.getWritableDatabase();
    }

    ContentValues generaValoresUsuario(String user, String pwd, String status,Integer oficina){
        ContentValues valores = new ContentValues();
        valores.put(CN_USUARIO_NOMBRE,user);
        valores.put(CN_USUARIO_CONTRASENA,pwd);
        valores.put(CN_USUARIO_STATUS,status);
        valores.put(CN_USUARIO_OFICINA,oficina);
        return  valores;
    }

    public Boolean comprobarUsuario(String user, String pwd) {
        String[] args =new String[]{user,pwd};
        Boolean valido = true;

        Cursor resultSet = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CN_USUARIO_NOMBRE + " = ? and " + CN_USUARIO_CONTRASENA +" =? \n"
                + " and " + CN_USUARIO_STATUS + " =1",args);

        if(resultSet.getCount()>0){valido=true;}else{valido=false;}

        return valido;
    }

    public Cursor extraeOficina(String user) {
        return db.rawQuery("SELECT " + CN_USUARIO_OFICINA + " FROM " + TABLE_NAME + " WHERE " + CN_USUARIO_NOMBRE +  "='" + user + "'",null);
    }

    public Cursor extraeUsuario() {
        //Log.d("SQL ","SELECT * FROM " + TABLE_NAME);
        return db.rawQuery("SELECT " + CN_USUARIO_NOMBRE + " FROM " + TABLE_NAME,null);
    }

    public void insertarUsuario(String user, String pwd, String status,Integer oficina) {
        Log.d("",db.insert(TABLE_NAME,null,generaValoresUsuario(user,pwd,status,oficina))+"");
    }

    public void borraTodoUsuario() {
        db.execSQL("DELETE FROM " + TABLE_NAME + ";");
    }
}
