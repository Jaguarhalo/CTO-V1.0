package com.example.corp_01agmp.ctov10;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corp_01agmp.ctov10.BD.Vencimientos;
import com.example.corp_01agmp.ctov10.Recursos.Mensajes;

import java.util.ArrayList;

public class Vencimiento extends AppCompatActivity {
    ListView listView;
    ArrayList id,cliente,pagare,fvencimiento,monto,capital,interes,moratorios,fcorte;
    Mensajes msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vencimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializar();
    }

    private void inicializar() {
        listView = (ListView) findViewById(R.id.lvVencimientos);
        id = new ArrayList();
        cliente = new ArrayList();
        pagare = new ArrayList();
        fvencimiento = new ArrayList();
        monto = new ArrayList();
        capital = new ArrayList();
        interes = new ArrayList();
        moratorios = new ArrayList();
        fcorte  = new ArrayList();

        descargainfo();
    }

    private void descargainfo() {
        Vencimientos venc = new Vencimientos(this);
        Cursor c = null;
        id.clear();
        cliente.clear();
        pagare.clear();
        fvencimiento.clear();
        monto.clear();
        capital.clear();
        interes.clear();
        moratorios.clear();
        fcorte.clear();

        c = venc.cargaVencimientos();
        msg = new Mensajes();

        if(c.moveToFirst()){
            do {
                id.add(c.getString(0).toString());
                cliente.add(c.getString(1).toString());
                pagare.add(c.getString(2).toString());
                fvencimiento.add(c.getString(3).toString());
                monto.add(c.getString(4).toString());
                capital.add(c.getString(5).toString());
                interes.add(c.getString(6).toString());
                moratorios.add(c.getString(7).toString());
                fcorte.add(c.getString(8).toString());
            }while (c.moveToNext());

            listView.setAdapter(new cargaAdapter(this));
        }else{
            Toast.makeText(this,"No se obtuvo información. Favor de sincronizar.",Toast.LENGTH_SHORT).show();}
    }


    private class cargaAdapter extends BaseAdapter {
        Context ctx;
        LayoutInflater layoutInflater;
        TextView idclientev,pagarev,nombrev,fechav,montov,capitalv,interesv,moratoriosv,fcortev;

        public cargaAdapter(Context ctx) {
            this.ctx = ctx;
            layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return id.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewGroup viewGroup = (ViewGroup)layoutInflater.inflate(R.layout.vencimientoscard,null);

            idclientev = (TextView)viewGroup.findViewById(R.id.tvVId);
            nombrev = (TextView)viewGroup.findViewById(R.id.tvVNombre);
            pagarev = (TextView)viewGroup.findViewById(R.id.tvVPagare);
            fechav = (TextView)viewGroup.findViewById(R.id.tvVFVencimiento);
            montov = (TextView)viewGroup.findViewById(R.id.tvVMonto);
            capitalv = (TextView)viewGroup.findViewById(R.id.tvVCapital);
            interesv = (TextView)viewGroup.findViewById(R.id.tvVInteres);
            moratoriosv = (TextView)viewGroup.findViewById(R.id.tvVMoratorios);
            fcortev = (TextView)viewGroup.findViewById(R.id.tvVFCorte);

            idclientev.setText("Id: " + id.get(position).toString());
            nombrev.setText("Nombre: " + cliente.get(position).toString());
            pagarev.setText("Pagare: " + pagare.get(position).toString());
            fechav.setText("Fecha: " + fvencimiento.get(position).toString());
            montov.setText("Monto: " + Double.valueOf(monto.get(position).toString()));
            capitalv.setText("Capital: " + Double.valueOf(capital.get(position).toString()));
            interesv.setText("Interes: " + Double.valueOf(interes.get(position).toString()));
            moratoriosv.setText("Moratorios: " + Double.valueOf(moratorios.get(position).toString()));
            fcortev.setText("Fecha Corte: " + fcorte.get(position).toString());

            return viewGroup;
        }
    }

}
