package com.example.corp_01agmp.ctov10;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.corp_01agmp.ctov10.BD.GuardarCliente;
import com.example.corp_01agmp.ctov10.BD.Socio;
import com.example.corp_01agmp.ctov10.Recursos.Mensajes;

import java.util.ArrayList;

public class Busqueda extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    EditText clientebuscar;
    Button btnBuscar;
    ListView listaclienteBuscado;
    ArrayList listaCliente;
    Mensajes msg;
    GuardarCliente gc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inicializar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inicializar() {
        clientebuscar = (EditText) findViewById(R.id.etCliente);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        listaclienteBuscado = (ListView) findViewById(R.id.lvClienteBuscado);
        listaCliente = new ArrayList();
        msg = new Mensajes();
        gc = new GuardarCliente(this);

        listaCliente.clear();
        clientebuscar.setText("");
        btnBuscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(clientebuscar.getText().toString().equals("")){
            msg.mostrarMensaje(v,this,"Favor de indicar el nombre del cliente a buscar");
        }else{
            OpenDialogoEligeCliente(clientebuscar.getText().toString(),v);
        }
    }

    private void OpenDialogoEligeCliente(String nombre, View v) {
        Socio socio = new Socio(this);
        Cursor c = null;

        listaCliente.clear();
        c = socio.buscaSocio(nombre);

        msg.mostrarMensaje(v,this,"Se encontraron " + c.getCount() + " coincidencias");

        if(c.getCount() > 0){
            if(c.moveToFirst()){
                do{
                    listaCliente.add(c.getString(0));
                }while(c.moveToNext());
                //msg.mostrarMensaje(v,MainActivity.this,"Mostrare el resultado.");
            }
            eligecliente(listaCliente);

        }else{
            msg.mostrarMensaje(v,this,"No se encontraron clientes con ese nombre. Favor de verificar bien el nombre " +
                    "o sincronize para mejor busqueda");
        }
    }

    private void eligecliente(ArrayList lClientes) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lClientes);
        listaclienteBuscado.setAdapter(adapter);
        listaclienteBuscado.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String valor = (String) parent.getItemAtPosition(position);

        gc.borraTodoBCliente();
        gc.insertarBCliente(valor);
        Log.d("Dato seleccionado ", valor.toString());
        this.finish();
    }
}
