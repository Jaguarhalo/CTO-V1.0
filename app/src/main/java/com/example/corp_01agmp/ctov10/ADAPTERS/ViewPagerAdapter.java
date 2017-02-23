package com.example.corp_01agmp.ctov10.ADAPTERS;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by corp_01agmp on 09/02/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabTitulos = new ArrayList<>();
    public void addFragments(Fragment fragments, String titulos){
        this. fragments.add(fragments);
        this.tabTitulos.add(titulos);
    }
    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }

    public CharSequence getPageTitle(int position){
        return tabTitulos.get(position);
    }
}
