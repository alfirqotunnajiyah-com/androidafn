package com.afn.afnapp.fragment;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.activity.LicenseActivity;
import com.franmontiel.attributionpresenter.AttributionPresenter;
import com.franmontiel.attributionpresenter.entities.Attribution;
import com.franmontiel.attributionpresenter.entities.Library;
import com.franmontiel.attributionpresenter.entities.License;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfil extends Fragment {


    private TextView tvName;
    private TextView tvVersion;
    private TextView tvTentang;
    private LinearLayout llOpenSource;
    private LinearLayout llLisensiIkon;
    private AttributionPresenter attributionPresenter;

    public FragmentProfil() {
        // Required empty public constructor
    }

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profil, container, false);
        initView();

        try {
            PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            String version = pInfo.versionName;
            tvVersion.setText("Versi " + version);
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();
        }

        initClick();


        attributionPresenter = new AttributionPresenter.Builder(getActivity())
                .addAttributions(
                        new Attribution.Builder("Android Developer Library")
                                .addCopyrightNotice("developer.android.com")
                                .setWebsite("https://developer.android.com")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("AHBottomNavigation library for Android")
                                .addCopyrightNotice("Copyright (c) 2018 Aurelien Hubert")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/aurelhubert/ahbottomnavigation")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("joda-time-android")
                                .addCopyrightNotice("Copyright (c) 2014 Daniel Lew")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/dlew/joda-time-android")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Nine Old Androids")
                                .addCopyrightNotice("Copyright (c) 2012 Jake Wharton")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/JakeWharton/NineOldAndroids")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Android Image Slider")
                                .addCopyrightNotice("Copyright (c) 2014 Daimajia")
                                .addLicense(License.MIT)
                                .setWebsite("https://github.com/daimajia/AndroidImageSlider")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("ExpandableHeightListView")
                                .addCopyrightNotice("Copyright 2015 Paolo Rotolo")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/paolorotolo/ExpandableHeightListView")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("BubbleSeekBar")
                                .addCopyrightNotice("Copyright 2017 woxingxiao")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/woxingxiao/BubbleSeekBar")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Android SQLiteAssetHelper")
                                .addCopyrightNotice("Copyright (C) 2011 readyState Software Ltd")
                                .addCopyrightNotice("Copyright (C) 2007 The Android Open Source Project")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/jgilfelt/android-sqlite-asset-helper")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("EasyPermissions")
                                .addCopyrightNotice("Copyright 2017 Google")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/googlesamples/easypermissions")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("AnimatedRecyclerView")
                                .addCopyrightNotice("Copyright (c) 2016 Sergey Glebov")
                                .addLicense(License.MIT)
                                .setWebsite("https://github.com/MLSDev/AnimatedRecyclerView")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Android NumberProgressBar")
                                .addCopyrightNotice("Copyright (c) 2014 Daimajia")
                                .addLicense(License.MIT)
                                .setWebsite("https://github.com/daimajia/NumberProgressBar")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("RxJava: Reactive Extensions for the JVM")
                                .addCopyrightNotice("Copyright (c) 2016-present, RxJava Contributors.")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/ReactiveX/RxJava")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Fresco")
                                .addCopyrightNotice("Copyright (c) Facebook, Inc. and its affiliates.")
                                .addLicense(License.MIT)
                                .setWebsite("https://github.com/facebook/fresco")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Volley")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/google/volley")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("DialogPlus")
                                .addCopyrightNotice("Copyright 2016 Orhan Obut")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/orhanobut/dialogplus")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("StepView")
                                .addCopyrightNotice("Copyright 2017 Bogdan Kornev.")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/shuhart/StepView")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("JUnit")
                                .addCopyrightNotice("Eclipse Public License 1.0")
                                .setWebsite("https://junit.org/junit4")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("AttributionPresenter")
                                .addCopyrightNotice("Copyright 2017 Francisco José Montiel Navarro")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/franmontiel/AttributionPresenter")
                                .build()
                )
                .addAttributions(
                        Library.PICASSO,
                        Library.RETROFIT,
                        Library.RX_JAVA,
                        Library.GSON,
                        Library.OK_HTTP,
                        Library.RX_ANDROID)
                .build();
        return view;
    }

    private void initClick() {
        llOpenSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attributionPresenter.showDialog("Lisensi");
            }
        });

        llLisensiIkon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LicenseActivity.class));
            }
        });
    }

    private void initView() {
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvVersion = (TextView) view.findViewById(R.id.tvVersion);
        tvTentang = (TextView) view.findViewById(R.id.tvTentang);
        llOpenSource = (LinearLayout) view.findViewById(R.id.llOpenSource);
        llLisensiIkon = (LinearLayout) view.findViewById(R.id.llLisensiIkon);
    }
}
