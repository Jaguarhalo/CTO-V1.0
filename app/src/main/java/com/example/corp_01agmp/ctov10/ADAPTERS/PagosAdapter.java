package com.example.corp_01agmp.ctov10.ADAPTERS;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.corp_01agmp.ctov10.Data.dPagos;
import com.example.corp_01agmp.ctov10.R;

import java.util.List;

/**
 * Created by corp_01agmp on 21/02/2017.
 */

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder>{
    List<dPagos> payment;
    Context ctx;

    public PagosAdapter(List<dPagos> pay, Context ctx) {
        this.payment = pay;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cvpagos,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.id.setText("IdCliente: " + String.valueOf(payment.get(position).getId()));
        holder.contrato.setText("Contrato: " + payment.get(position).getContrato());
        holder.pagare.setText("Pagare: " + payment.get(position).getPagare());
        holder.fPago.setText("Fecha: " + payment.get(position).getfPago());
        holder.monto.setText("Monto: " + payment.get(position).getMonto());
    }

    @Override
    public int getItemCount() {
        if(payment.size() <= 0){return 0;}else{return payment.size();}
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        TextView id,contrato,pagare,fPago,monto;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cvMuestraPagos);
            id = (TextView) itemView.findViewById(R.id.tvCVPIdCliente);
            contrato = (TextView) itemView.findViewById(R.id.tvCVPContrato);
            pagare = (TextView) itemView.findViewById(R.id.tvCVPPagare);
            fPago = (TextView) itemView.findViewById(R.id.tvCVPFPago);
            monto = (TextView) itemView.findViewById(R.id.tvCVPPago);
        }
    }

}
