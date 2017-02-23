package com.example.corp_01agmp.ctov10.ParsersSqlite;

import android.content.Context;
import android.database.Cursor;

import com.example.corp_01agmp.ctov10.BD.Socio;
import com.example.corp_01agmp.ctov10.Data.dClientes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corp_01agmp on 20/02/2017.
 */

public class ClienteParserSQLite {
    Context ctx;

    public ClienteParserSQLite(Context ctx) {
        this.ctx = ctx;
    }

    public List<dClientes> parserFeedClienteData(String nombre){
        Socio s = new Socio(this.ctx);
        Cursor c = null;
        List<dClientes> cliente = new ArrayList<>();
        c = s.cargaDatosSocio(nombre);

        if(c.moveToFirst()){
            do{
                dClientes dcliente = new dClientes();
                dcliente.setIdcliente(c.getString(0).toString());
                dcliente.setNombre(c.getString(1).toString());
                dcliente.setRfc(c.getString(2).toString());
                dcliente.setCurp(c.getString(3).toString());
                dcliente.setEdad(c.getString(4).toString());
                dcliente.setDireccion(c.getString(5).toString() + " CP " + c.getString(6).toString());
                dcliente.setTelefono(c.getString(7).toString());
                dcliente.setMail(c.getString(9).toString());
                cliente.add(dcliente);
            }while (c.moveToNext());
        }
        else{ cliente = null;}
        return cliente;
    }
}
