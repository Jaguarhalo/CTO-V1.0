package com.example.corp_01agmp.ctov10.Parsers;

import android.content.Context;

import com.example.corp_01agmp.ctov10.BD.UsuarioBD;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.corp_01agmp.ctov10.BD.UsuarioBD;

/**
 * Created by kratos on 24/01/2017.
 */

public class LoginParser {
    public boolean parser(String content, Context ctx, String pwd) {
        boolean resultado = false;
        try {
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getString("Usuario").equals("1")){
                    resultado = false;
                }else{
                    resultado = true;
                    UsuarioBD u = new UsuarioBD(ctx);
                    u.borraTodoUsuario();
                    u.insertarUsuario(jsonObject.getString("Usuario"),pwd,jsonObject.getString("Estado"),
                            jsonObject.getInt("Oficina"));
                }
            }

            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return resultado;
        }
    }

}
