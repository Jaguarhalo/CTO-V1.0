package com.example.corp_01agmp.ctov10;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.corp_01agmp.ctov10.BD.UsuarioBD;
import com.example.corp_01agmp.ctov10.HTTPManager.HTTPManager;
import com.example.corp_01agmp.ctov10.Parsers.LoginParser;
import com.example.corp_01agmp.ctov10.Recursos.Mensajes;
import com.example.corp_01agmp.ctov10.Recursos.UrlString;
import com.example.corp_01agmp.ctov10.Recursos.ValidaInternet;


public class SplashScreen extends AppCompatActivity implements View.OnClickListener {

    EditText user,contra;
    Button btnLogin;
    Mensajes msg = new Mensajes();
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        inicializarcontroles();
    }

    private void inicializarcontroles() {
        user = (EditText)findViewById(R.id.etUsuario);
        contra = (EditText)findViewById(R.id.etContrasena);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        pb = (ProgressBar)findViewById(R.id.progressbar);

        btnLogin.setOnClickListener(this);
        pb.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        if(user.getText().toString().equals("") || contra.getText().toString().equals("")){
            msg.mostrarMensaje(view,this,"Debe de llenar todos los campos.");
        }else{
            validaLogin(view);
        }
    }

    private void validaLogin(View v) {
        ValidaInternet valida = new ValidaInternet();

        if(valida.conectadoWifi(this)){
            validaws(v);
        }else{
            pb.setVisibility(View.VISIBLE);
            validaLocal(v);
        }
    }

    private void validaLocal(View v) {
        UsuarioBD u = new UsuarioBD(getApplicationContext());
        boolean validado =  u.comprobarUsuario(user.getText().toString(),contra.getText().toString());
        if(validado){
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            pb.setVisibility(View.GONE);
            startActivity(intent);
            finish();
        }else{
            pb.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            msg.mostrarMensaje(v,this,"Se tendra que conectar a  internet para validar su Usuarios.");
        }
    }

    private void validaws(View v) {
        UrlString uri = new UrlString();
        String urifinal = uri.getLogin() + "?Usuario=" + user.getText().toString() + "&Contrasena=" + contra.getText().toString();
        pb.setVisibility(View.VISIBLE);
        MyTask mytask = new MyTask();
        mytask.execute(urifinal);
    }

    private class MyTask extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btnLogin.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String content = HTTPManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            boolean resultado;
            LoginParser loginParser = new LoginParser();

            if(s == null){
                pb.setVisibility(View.GONE);
                Toast.makeText(SplashScreen.this ,"No se pudo conectactar al servidor",Toast.LENGTH_LONG).show();
                pb.setVisibility(View.GONE);
                btnLogin.setEnabled(true);
                return;
            }else{
                 resultado = loginParser.parser(s,getApplicationContext(),contra.getText().toString());
                if(resultado){
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(SplashScreen.this ,"Ususario/Contraseña Invalida.",Toast.LENGTH_LONG).show();
                }
                pb.setVisibility(View.GONE);
                btnLogin.setEnabled(true);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
        }
    }


}
