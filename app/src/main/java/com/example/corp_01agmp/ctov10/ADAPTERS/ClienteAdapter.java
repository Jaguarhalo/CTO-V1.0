package com.example.corp_01agmp.ctov10.ADAPTERS;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.corp_01agmp.ctov10.Data.dClientes;
import com.example.corp_01agmp.ctov10.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corp_01agmp on 17/02/2017.
 */

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder>{
    List<dClientes> client;
    Context ctx;

    public ClienteAdapter(List<dClientes> client, Context ctx) {
        this.client = client;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cvcliente,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.idclient.setText("Id: " + String.valueOf(client.get(position).getIdcliente()));
        holder.name.setText("Nombre: " + client.get(position).getNombre());
        holder.age.setText("Edad: " + String.valueOf(client.get(position).getEdad()));
        holder.fcr.setText("RFC: " + client.get(position).getRfc());
        holder.cedula.setText("CURP: " + client.get(position).getCurp());
        holder.adress.setText("Dirección: " + client.get(position).getDireccion());
        holder.phone.setText("Teléfono: " + client.get(position).getTelefono());
        holder.email.setText("Email: " + client.get(position).getMail());
    }

    @Override
    public int getItemCount() {
        if(client.size() <= 0){return 0;}else{return client.size();}

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        TextView idclient,name,adress,phone,fcr,cedula,age,email;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cvMuestraCliente);
            idclient = (TextView) itemView.findViewById(R.id.tvCVIdCliente);
            name = (TextView) itemView.findViewById(R.id.tvCVNombre);
            age = (TextView) itemView.findViewById(R.id.tvCVEdad);
            fcr = (TextView) itemView.findViewById(R.id.tvCVRFC);
            cedula = (TextView) itemView.findViewById(R.id.tvCVCURP);
            adress = (TextView) itemView.findViewById(R.id.tvCVDireccion);
            phone = (TextView) itemView.findViewById(R.id.tvCVTelefono);
            email = (TextView) itemView.findViewById(R.id.tvCVMail);
        }

    }
}
