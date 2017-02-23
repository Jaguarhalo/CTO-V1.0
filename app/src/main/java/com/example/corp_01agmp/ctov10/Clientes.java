package com.example.corp_01agmp.ctov10;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.corp_01agmp.ctov10.ADAPTERS.ClienteAdapter;
import com.example.corp_01agmp.ctov10.ADAPTERS.ViewPagerAdapter;
import com.example.corp_01agmp.ctov10.Data.dClientes;
import com.example.corp_01agmp.ctov10.ParsersSqlite.ClienteParserSQLite;
import com.example.corp_01agmp.ctov10.Recursos.Cursores;

import java.util.ArrayList;
import java.util.List;

import layout.CreditosClienteFragment;
import layout.DatosClienteFragment;
import layout.PagosClienteFragment;

public class Clientes extends AppCompatActivity implements DatosClienteFragment.OnFragmentInteractionListener,
    CreditosClienteFragment.OnFragmentInteractionListener,PagosClienteFragment.OnFragmentInteractionListener{
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewpageradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabsCliente);
        viewPager =(ViewPager) findViewById(R.id.viewPagerCliente);
        viewpageradapter = new ViewPagerAdapter(getSupportFragmentManager());


        viewpageradapter.addFragments(new DatosClienteFragment(),"Datos Generales");
        viewpageradapter.addFragments(new CreditosClienteFragment(),"creditos");
        viewpageradapter.addFragments(new PagosClienteFragment(),"Pagos");

        viewPager.setAdapter(viewpageradapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
