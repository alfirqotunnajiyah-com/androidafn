package com.afn.afnapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.afn.afnapp.R;
import com.afn.afnapp.fragment.FragmentIsiDzikirActivity;

import java.util.ArrayList;

public class DzikirPagiDanPetangActivity extends AppCompatActivity {

    private android.support.v4.view.ViewPager vPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ArrayList<FragmentIsiDzikirActivity> fragments = new ArrayList<>();

    private String[] judulDzikir = {
            "1. Ta'awudz",
            "2. Ayat Kursi",
    };

    private String[] isiBacaanDzikir = {
            "أَعُوذُ بِاللَّهِ مِنْ الشَّيْطَانِ الرَّجِيمِ",
            "اللَّهُ لاَ إِلَهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ، لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ، لَهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ، مَنْ ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ، يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ، وَلَا يُحِيطُونَ بِشَيْءٍ مِنْ عِلْمِهِ إِلاَّ بِمَا شَاءَ، وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ، وَلَا يَئُودُهُ حِفْظُهُمَا، وَهُوَ الْعَلِيُّ الْعَظِيم"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzikir_pagi_dan_petang);

        setLayout();
        getData();
    }

    void setLayout() {
        this.vPager = (ViewPager) findViewById(R.id.vPager);
    }

    void getData() {
        FragmentIsiDzikirActivity ff = new FragmentIsiDzikirActivity();
        fragmentPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        for (int i = 0; i < judulDzikir.length; i++) {
            ((MyPagerAdapter) fragmentPagerAdapter).addFragment(ff, judulDzikir[i], isiBacaanDzikir[i]);
        }
        vPager.setAdapter(fragmentPagerAdapter);
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<FragmentIsiDzikirActivity> listFragment = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fragmentManager, ArrayList<FragmentIsiDzikirActivity> fragments) {
            super(fragmentManager);
            listFragment = fragments;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return listFragment.size();
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        public void addFragment(FragmentIsiDzikirActivity fragment, String judulNa, String isiNa) {
            listFragment.add(fragment.newInstance(judulNa, isiNa));
        }
    }
}
