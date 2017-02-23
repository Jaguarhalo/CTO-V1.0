package com.example.corp_01agmp.ctov10.ParsersSqlite;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.corp_01agmp.ctov10.BD.Saldo;
import com.example.corp_01agmp.ctov10.Data.dCreditos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corp_01agmp on 21/02/2017.
 */

public class CreditosParserSQLite {
    Context ctx;

    public CreditosParserSQLite(Context ctx) {
        this.ctx = ctx;
    }

    public List<dCreditos> parserFeedCreditoData(Integer id){
        Saldo s = new Saldo(this.ctx);
        Cursor c = null;
        List<dCreditos> creditos = new ArrayList<>();
        c = s.cargaCreditos(id);
        Log.d("Datos del Cursor:",String.valueOf(c));

        if(c.getCount()>0){
            if(c.moveToFirst()){
                do{
                    dCreditos dcreditos = new dCreditos();

                    dcreditos.setIdCliente(c.getInt(0));
                    dcreditos.setContrato(c.getString(1).toString());
                    dcreditos.setPagare(c.getString(2).toString());
                    dcreditos.setfDesembolso(c.getString(3).toString());
                    dcreditos.setfVencimiento(c.getString(4).toString());
                    dcreditos.settInteres(c.getInt(5));
                    dcreditos.settMoratoria(c.getInt(6));
                    dcreditos.setDiasMora(c.getInt(7));
                    dcreditos.setMonto(c.getDouble(8));
                    dcreditos.setCapital(c.getDouble(9));
                    dcreditos.setInteres(c.getDouble(10));
                    dcreditos.setMoratorios(c.getDouble(11));
                    dcreditos.setfCorte(c.getString(12).toString());

                    creditos.add(dcreditos);
                }while (c.moveToNext());
            }


        }else{creditos.clear();}

        return creditos;
    }
}
