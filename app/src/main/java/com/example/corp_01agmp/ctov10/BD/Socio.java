package com.example.corp_01agmp.ctov10.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Usuario 1 on 12/05/2016.
 */
public class Socio {

    private Conexion conexion;
    private SQLiteDatabase db;

    /*              Variables de la Clase Saldo           */
    private static final String TABLE_NAME = "tSocio";
    private static final String CN_IDSOCIO = "IdSocio";
    private static final String CN_NOMBRE = "nombre";
    private static final String CN_DIRECCION = "direccion";
    private static final String CN_RFC = "rfc";
    private static final String CN_CURP = "curp";
    private static final String CN_EDAD = "edad";
    private static final String CN_CP = "CP";
    private static final String CN_OCUPACION = "ocupacion";
    private static final String CN_MAIL = "mail";
    private static final String CN_TELEFONO = "telefono";



    /*              Creacion de la tabla Usuario           */

    public static String CREA_TABLE_SOCIO = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                                                CN_IDSOCIO + " INTEGER NOT NULL, " +
                                                CN_NOMBRE + " TEXT NOT NULL, " +
                                                CN_RFC + " TEXT NOT NULL, " +
                                                CN_CURP + " TEXT NOT NULL, " +
                                                CN_EDAD + " INTEGER NOT NULL, " +
                                                CN_DIRECCION + " TEXT NOT NULL, " +
                                                CN_CP + " TEXT NOT NULL, " +
                                                CN_TELEFONO + " TEXT NOT NULL, " +
                                                CN_OCUPACION + " TEXT NOT NULL, " +
                                                CN_MAIL + " TEXT NOT NULL);";


    /*              Funciones de la Clase Usuario           */

    public Socio(Context ctx) {
        conexion = new Conexion(ctx);
        db = conexion.getWritableDatabase();
    }


    ContentValues generaValoresSocio(Integer id, String nombre,String rfc,String curp, Integer edad, String direccion, String cp, String telefono, String ocupacion,
                                     String mail){

        ContentValues valores = new ContentValues();

        valores.put(CN_IDSOCIO,id);
        valores.put(CN_NOMBRE,nombre);
        valores.put(CN_RFC,rfc);
        valores.put(CN_CURP,curp);
        valores.put(CN_EDAD,edad);
        valores.put(CN_DIRECCION,direccion);
        valores.put(CN_CP,cp);
        valores.put(CN_TELEFONO,telefono);
        valores.put(CN_OCUPACION,ocupacion);
        valores.put(CN_MAIL,mail);

        return  valores;
    }

    public Boolean comprobarSocio() {
        Boolean valido = true;

        Cursor resultSet = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        if(resultSet.getCount()>0){valido=true;}else{valido=false;}

        return valido;
    }

    public void cerrar(){
        db.close();
    }
    /*
    public Cursor cargaSocio(Integer id) {
        //String[] args = new String[]{cliente};
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CN_IDSOCIO + "=" + id,null);
    }
    */
    public Cursor cargaDatosSocio(String nombre) {
        //String[] args = new String[]{cliente};
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CN_NOMBRE + "='" + nombre + "'",null);
    }

    public Cursor buscaSocio(String nombre) {
        //String[] args = new String[]{cliente};
        return db.rawQuery("SELECT " + CN_NOMBRE + " FROM " + TABLE_NAME + " WHERE " + CN_NOMBRE + " LIKE '%" + nombre + "%'",null);
    }

    public void insertarSocio(Integer id, String nombre, String rfc, String curp, Integer edad,String direccion, String cp, String telefono, String ocupacion, String mail) {
        db.insert(TABLE_NAME,null,generaValoresSocio(id,nombre,rfc,curp,edad,direccion,cp,telefono,ocupacion,mail));
        //Log.d("Inserto: ",id + ".- " + nombre);
    }

    public Cursor buscaSocioId(String nombre) {
        //String[] args = new String[]{cliente};
        return db.rawQuery("SELECT " + CN_IDSOCIO + " FROM " + TABLE_NAME + " WHERE " + CN_NOMBRE + " = '" + nombre + "'",null);
    }

    public void borraTodoSocio() {
        db.execSQL("DELETE FROM " + TABLE_NAME + ";");
    }
}
