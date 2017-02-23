package com.example.corp_01agmp.ctov10.Data;

/**
 * Created by corp_01agmp on 20/02/2017.
 */

public class dCreditos {
    String contrato, pagare,fDesembolso,fVencimiento, fCorte;
    Integer idCliente,tInteres, tMoratoria, diasMora;
    Double monto, capital, interes, moratorios;

    public dCreditos() {
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getPagare() {
        return pagare;
    }

    public void setPagare(String pagare) {
        this.pagare = pagare;
    }

    public String getfDesembolso() {
        return fDesembolso;
    }

    public void setfDesembolso(String fDesembolso) {
        this.fDesembolso = fDesembolso;
    }

    public String getfVencimiento() {
        return fVencimiento;
    }

    public void setfVencimiento(String fVencimiento) {
        this.fVencimiento = fVencimiento;
    }

    public String getfCorte() {
        return fCorte;
    }

    public void setfCorte(String fCorte) {
        this.fCorte = fCorte;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer gettInteres() {
        return tInteres;
    }

    public void settInteres(Integer tInteres) {
        this.tInteres = tInteres;
    }

    public Integer gettMoratoria() {
        return tMoratoria;
    }

    public void settMoratoria(Integer tMoratoria) {
        this.tMoratoria = tMoratoria;
    }

    public Integer getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(Integer diasMora) {
        this.diasMora = diasMora;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public Double getMoratorios() {
        return moratorios;
    }

    public void setMoratorios(Double moratorios) {
        this.moratorios = moratorios;
    }
}
