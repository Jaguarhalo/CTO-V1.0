package com.example.corp_01agmp.ctov10.Parsers;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;
import com.example.corp_01agmp.ctov10.BD.Socio;
import com.example.corp_01agmp.ctov10.BD.UsuarioBD;
import com.example.corp_01agmp.ctov10.Recursos.Mensajes;
import com.example.corp_01agmp.ctov10.Recursos.UrlString;

/**
 * Created by kratos on 25/01/2017.
 */

public class SocioParser {
    Context ctx;
    Socio s ;
    UrlString url;
    UsuarioBD user;

    public SocioParser(Context context){
        ctx = context;
        s = new Socio(ctx);
        url = new UrlString();
        user = new UsuarioBD(ctx);
        Mensajes msg;
    }

    public void extraeSocios(){
        Cursor u = user.extraeUsuario();
        final String res1 = getDataCursor(u);
        final String urlfinal = url.getSocios();
        final ProgressDialog progress = new ProgressDialog(ctx);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("Opcion","1");
        params.add("Oficina",getDataCursor(user.extraeOficina(res1)));

        client.setTimeout(2500);

        RequestHandle post = client.get(urlfinal,params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                Log.d("DEBUG", "iniciando descarga");
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    progress.show();
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(new String(responseBody));
                        s.borraTodoSocio();

                        for(int i=0;i< jsonArray.length();i++){
                            s.insertarSocio(jsonArray.getJSONObject(i).getInt("Id"),jsonArray.getJSONObject(i).getString("Cliente"),
                                    jsonArray.getJSONObject(i).getString("RFC"),jsonArray.getJSONObject(i).getString("CURP"),
                                    jsonArray.getJSONObject(i).getInt("EDAD"),jsonArray.getJSONObject(i).getString("DOMICILIO"),
                                    jsonArray.getJSONObject(i).getString("CP"),jsonArray.getJSONObject(i).getString("TELEFONO"),
                                    jsonArray.getJSONObject(i).getString("OCUPACION"),jsonArray.getJSONObject(i).getString("EMAIL"));

                        }

                        Log.d("DEBUG", "Inserto Socios");
                        progress.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("DEBUG",e.getMessage().toString());
                    }
                }else{
                    Log.d("DEBUG","No se tuvo conexion");
                }
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
