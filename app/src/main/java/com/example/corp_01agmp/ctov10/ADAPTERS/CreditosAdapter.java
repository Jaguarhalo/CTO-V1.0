package com.example.corp_01agmp.ctov10.ADAPTERS;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.corp_01agmp.ctov10.Data.dClientes;
import com.example.corp_01agmp.ctov10.Data.dCreditos;
import com.example.corp_01agmp.ctov10.R;

import java.util.List;

/**
 * Created by corp_01agmp on 17/02/2017.
 */

public class CreditosAdapter extends RecyclerView.Adapter<CreditosAdapter.ViewHolder>{
    List<dCreditos> credits;
    Context ctx;

    public CreditosAdapter(List<dCreditos> credits, Context ctx) {
        this.credits = credits;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cvcredtitos,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.idClient.setText("Id: " + String.valueOf(credits.get(position).getIdCliente()));
        holder.launs.setText("Contrato: " + credits.get(position).getContrato());
        holder.payment.setText("Pagare: " + credits.get(position).getPagare());
        holder.fDesemb.setText("Fecha Desembolso: " + credits.get(position).getfDesembolso());
        holder.fVen.setText("Fecha Vencimiento: " + credits.get(position).getfVencimiento());
        holder.tInt.setText("Tasa Interes: " + credits.get(position).gettInteres() + "%");
        holder.tMora.setText("Tasa Moratoria: " + credits.get(position).gettMoratoria() + "%");
        holder.diasMo.setText("Dias Mora: " + credits.get(position).getDiasMora());
       holder.montodem.setText("Monto Desembolsado: $ " + credits.get(position).getMonto());
        holder.capi.setText("Capital: $ " + credits.get(position).getCapital());
        holder.inte.setText("Interes: $ " + credits.get(position).getInteres());
        holder.mora.setText("Moratorios $ : " + credits.get(position).getMoratorios());
        holder.total.setText("Total: $ " + (credits.get(position).getCapital()+ credits.get(position).getInteres() + credits.get(position).getMoratorios()));
        holder.fCorte.setText("Fecha Corte: " + credits.get(position).getfCorte());
    }

    @Override
    public int getItemCount() {
        if(credits.size() <= 0){return 0;}else{return credits.size();}

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        TextView launs, payment,fDesemb,fVen, fCorte,idClient, tInt, tMora, diasMo,montodem,capi,inte, mora,total;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cvMuestraCreditos);
            idClient = (TextView) itemView.findViewById(R.id.tvCVIdCliente);
            launs = (TextView) itemView.findViewById(R.id.tvCVContrato);
            payment = (TextView) itemView.findViewById(R.id.tvCVPagare);
            fDesemb = (TextView) itemView.findViewById(R.id.tvCVFDesembolso);
            fVen = (TextView) itemView.findViewById(R.id.tvCVFVencimiento);
            tInt = (TextView) itemView.findViewById(R.id.tvCVTinteres);
            tMora = (TextView) itemView.findViewById(R.id.tvCVTMora);
            diasMo = (TextView) itemView.findViewById(R.id.tvCVDiasMora);
            montodem = (TextView) itemView.findViewById(R.id.tvCVMonto);
            capi = (TextView) itemView.findViewById(R.id.tvCVCapital);
            inte = (TextView) itemView.findViewById(R.id.tvCVInteres);
            mora = (TextView) itemView.findViewById(R.id.tvCVTMoratorios);
            total = (TextView) itemView.findViewById(R.id.tvCVTotal);
            fCorte = (TextView) itemView.findViewById(R.id.tvCVFCorte);
        }

    }
}
