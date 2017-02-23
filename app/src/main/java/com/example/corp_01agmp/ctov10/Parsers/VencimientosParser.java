package com.example.corp_01agmp.ctov10.Parsers;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;
import com.example.corp_01agmp.ctov10.BD.UsuarioBD;
import com.example.corp_01agmp.ctov10.BD.Vencimientos;
import com.example.corp_01agmp.ctov10.Recursos.Mensajes;
import com.example.corp_01agmp.ctov10.Recursos.UrlString;

/**
 * Created by corp_01agmp on 08/02/2017.
 */

public class VencimientosParser {

    Context ctx;
    Vencimientos v;
    UrlString url;
    UsuarioBD user;

    public VencimientosParser(Context context) {
        ctx = context;
        v = new Vencimientos(ctx);
        url = new UrlString();
        user = new UsuarioBD(ctx);

    }

    public void extraeVencimientos(){
        Cursor u = user.extraeUsuario();
        final String res1 = getDataCursor(u);
        final String urlfinal = url.getVencimientos();


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("Opcion","1");
        params.add("Oficina",getDataCursor(user.extraeOficina(res1)));

        client.setTimeout(2500);

        RequestHandle post = client.get(urlfinal, params, new AsyncHttpResponseHandler() {
            Mensajes msg;
            @Override
            public void onStart() {
                Log.d("DEBUG", "iniciando descarga creditos");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(new String(responseBody));
                        v.borraTodoVencimientos();
                        for(int i=0;i< jsonArray.length();i++) {
                            v.insertarVencimientos(jsonArray.getJSONObject(i).getInt("Id"),jsonArray.getJSONObject(i).getString("Cliente"),
                                    jsonArray.getJSONObject(i).getString("Pagare"),jsonArray.getJSONObject(i).getString("FVencimiento"),
                                    jsonArray.getJSONObject(i).getDouble("Monto"),jsonArray.getJSONObject(i).getDouble("Capital"),
                                    jsonArray.getJSONObject(i).getDouble("Interes"),jsonArray.getJSONObject(i).getDouble("Moratorios"),
                                    jsonArray.getJSONObject(i).getString("Fcorte"));
                        }

                    } catch (Exception e) {
                        Log.d("DEBUG:", e.getMessage().toString());
                    }

                }else{
                    Log.d("DEBUG","No se tuvo conexion");
                }
                Log.d("DEBUG", "descarga de creditos finalizada");

                Boolean socval = v.comprobarVencimientos();
                Log.d("DEBUG: ", String.valueOf(socval));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(ctx,"No se pudieron obtener datos.",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getDataCursor(Cursor cursor){
        Cursor c = cursor;
        String resultado = "";

        if(c.getCount()>0){
            if(c.moveToFirst()){
                do{
                    resultado = c.getString(0).toString();
                }while(c.moveToNext());

            }
        }

        return resultado;
    }

}
