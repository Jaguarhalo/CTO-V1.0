package com.example.corp_01agmp.ctov10.Recursos;

/**
 * Created by kratos on 24/01/2017.
 */

public class UrlString {
    public String login;
    public String socios;
    public String creditos;
    public String pagos;
    public String vencimientos;

    public String getLogin() {
        return login ="http://convengamos.homedns.org/siofsfconvengamos/mobile/API2/Usuario/Login.php";
    }

    public String getSocios() {
        return socios="http://convengamos.homedns.org/siofsfconvengamos/mobile/API2/Cliente/Cliente.php";
    }

    public String getCreditos() {
        return creditos="http://convengamos.homedns.org/siofsfconvengamos/mobile/API2/Cliente/Creditos.php";
    }

    public String getPagos() {
        return pagos="http://convengamos.homedns.org/siofsfconvengamos/mobile/API2/Cliente/Pagos.php";
    }

    public String getVencimientos() {
        return vencimientos="http://convengamos.homedns.org/siofsfconvengamos/mobile/API2/Vencimientos/Vencimientos.php";
    }
}
