package com.example.corp_01agmp.ctov10.ParsersSqlite;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.corp_01agmp.ctov10.BD.Pagos;
import com.example.corp_01agmp.ctov10.Data.dCreditos;
import com.example.corp_01agmp.ctov10.Data.dPagos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corp_01agmp on 21/02/2017.
 */

public class PagosParserSQLite {
    Context ctx;

    public PagosParserSQLite(Context ctx) {
        this.ctx = ctx;
    }

    public List<dPagos> parserFeedPagosData(Integer id){
        Pagos p = new Pagos(this.ctx);
        Cursor c = null;
        List<dPagos> pagos = new ArrayList<>();
        c = p.cargaPagosContrato(id);

        if(c.getCount()>0){
            if(c.moveToNext()){
                do{
                    dPagos dpagos = new dPagos();
                    dpagos.setId(c.getInt(0));
                    dpagos.setContrato(c.getString(1).toString());
                    dpagos.setPagare(c.getString(2).toString());
                    dpagos.setfPago(c.getString(3).toString());
                    dpagos.setMonto(c.getDouble(4));
                    pagos.add(dpagos);
                }while (c.moveToNext());
            }else{
                pagos.clear();
            }
        }else{pagos.clear();}

        return pagos;
    }

}
