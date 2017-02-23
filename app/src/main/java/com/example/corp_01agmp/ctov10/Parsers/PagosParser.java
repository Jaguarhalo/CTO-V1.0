package com.example.corp_01agmp.ctov10.Parsers;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;
import com.example.corp_01agmp.ctov10.BD.Pagos;
import com.example.corp_01agmp.ctov10.BD.UsuarioBD;
import com.example.corp_01agmp.ctov10.Recursos.Mensajes;
import com.example.corp_01agmp.ctov10.Recursos.UrlString;

/**
 * Created by kratos on 27/01/2017.
 */

public class PagosParser {

    Context ctx;
    Pagos p ;
    UrlString url;
    UsuarioBD user;

    public PagosParser(Context context){
        ctx = context;
        p = new Pagos(ctx);
        url = new UrlString();
        user = new UsuarioBD(ctx);
        Mensajes msg;
    }

    public void extraePagos(){
        Cursor u = user.extraeUsuario();
        final String res1 = getDataCursor(u);
        final String urlfinal = url.getPagos();


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("Opcion","1");
        params.add("Oficina",getDataCursor(user.extraeOficina(res1)));

        client.setTimeout(2500);

        RequestHandle post = client.get(urlfinal, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(new String(responseBody));
                        p.borraTodoPagos();

                        for(int i=0;i< jsonArray.length();i++) {
                            /*Log.d("DEBUG:", String.valueOf(jsonArray.getJSONObject(i).getInt("Id")) + "," + jsonArray.getJSONObject(i).getString("Contrato")  + "," +
                                    jsonArray.getJSONObject(i).getString("Pagare")  + "," + jsonArray.getJSONObject(i).getString("Fecha") + "," +
                                    jsonArray.getJSONObject(i).getDouble("Monto"));*/

                            p.insertarPagos(jsonArray.getJSONObject(i).getInt("Id"),jsonArray.getJSONObject(i).getString("Contrato"),
                                    jsonArray.getJSONObject(i).getString("Pagare"),jsonArray.getJSONObject(i).getString("Fecha"),
                                    jsonArray.getJSONObject(i).getDouble("Monto"));
                        }

                    } catch (Exception e) {
                        Log.d("DEBUG:", e.getMessage().toString());
                    }

                }else{
                    Log.d("DEBUG","No se tuvo conexion");
                }
                Log.d("DEBUG", "descarga de pagos finalizada");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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


