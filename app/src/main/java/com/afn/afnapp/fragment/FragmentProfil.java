package com.afn.afnapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afn.afnapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfil extends Fragment {


    public FragmentProfil() {
        // Required empty public constructor
    }

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profil, container, false);
        /*Chronology iso = ISOChronology.getInstance(DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Jakarta")));
        Chronology hijri = IslamicChronology.getInstance(DateTimeZone.forTimeZone(TimeZone.getTimeZone("Asia/Makkah")), IslamicChronology.LEAP_YEAR_15_BASED);

        LocalDate todayIso = new LocalDate(2019, 8, 2, iso);
        LocalDate todayHijri = new LocalDate(todayIso.toDateTimeAtCurrentTime(),
                hijri);*/
        return view;
    }

}
