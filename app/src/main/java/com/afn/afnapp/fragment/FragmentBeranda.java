package com.afn.afnapp.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.afn.afnapp.MainActivity;
import com.afn.afnapp.R;
import com.afn.afnapp.activity.AlQuranFeature.AlQuranActivity;
import com.afn.afnapp.activity.ArahKiblat;
import com.afn.afnapp.activity.CalendarActivity;
import com.afn.afnapp.activity.DoaHarianFeature.ListDoaActivity;
import com.afn.afnapp.activity.DzikirPagiDanPetangActivity;
import com.afn.afnapp.activity.JadwalKajianFeature.JadwalKajianMainActivity;
import com.afn.afnapp.adapter.FiturAdapter;
import com.afn.afnapp.model.FiturModel;
import com.afn.afnapp.ui.jadwalsholat.JadwalSholat;
import com.afn.afnapp.ui.jadwalsholat.JadwalSholatActivity;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBeranda extends Fragment {

    //muslim.or.id
    private String haditsToday = "“Barangsiapa yang menempuh jalan untuk menuntut ilmu, Allah Ta’ala akan mudahkan baginya jalan menuju surga.” (HR. Muslim no. 2699)";

    public FragmentBeranda() {
        // Required empty public constructor
    }


    private View view;
    private SliderLayout slider;
    private PagerIndicator pagerIndicator;
    private ExpandableHeightGridView gvFitur;

    private List<FiturModel> listFitur = new ArrayList<>();
    private FiturAdapter fiturAdapter;

    private String[] listNamaFitur = {
            "Dzikir Pagi",
            "Dzikir Petang",
            "Al-Quran",
            "Arah Kiblat",
            "Jadwal Sholat",
            "Kalender Hijriah",
            "Jadwal Kajian",
            "Do'a Harian"
    };
    private int[] listGambarFitur = {
            R.drawable.ic_dzikir_pagi,
            R.drawable.ic_dzikir_petang,
            R.drawable.ic_quran,
            R.drawable.ic_compass,
            R.drawable.ic_mosque,
            R.drawable.ic_calendar,
            R.drawable.ic_kajian,
            R.drawable.ic_kajian
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_beranda, container, false);
        // Inflate the layout for this fragment

        fiturAdapter = new FiturAdapter(getActivity(), listFitur);
        fiturAdapter.notifyDataSetChanged();
        //MainActivity.toolbar.setVisibility(View.GONE);

        layoutNa();
        setGambarNa();
        setFiturNa();
        setKLik();

        return view;
    }

    void layoutNa() {
        slider = view.findViewById(R.id.slider);
        pagerIndicator = view.findViewById(R.id.custom_indicator);
        gvFitur = view.findViewById(R.id.gvFitur);

        gvFitur.setExpanded(true);
    }

    void setKLik() {
        gvFitur.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FiturModel ff = listFitur.get(position);
                if (ff.getFiturNama().equalsIgnoreCase("Dzikir Pagi")) {
                    Intent i = new Intent(getActivity(), DzikirPagiDanPetangActivity.class);
                    i.putExtra("waktu", 1);
                    startActivity(i);
                } else if (ff.getFiturNama().equalsIgnoreCase("Dzikir Petang")) {
                    Intent i = new Intent(getActivity(), DzikirPagiDanPetangActivity.class);
                    i.putExtra("waktu", 2);
                    startActivity(i);
                } else if (ff.getFiturNama().equalsIgnoreCase("Al-Quran")) {
                    Intent i = new Intent(getActivity(), AlQuranActivity.class);
                    startActivity(i);
                } else if (ff.getFiturNama().equalsIgnoreCase("Arah Kiblat")) {
                    Intent i = new Intent(getActivity(), ArahKiblat.class);
                    startActivity(i);
                } else if (ff.getFiturNama().equalsIgnoreCase("Jadwal Sholat")) {
                    Intent i = new Intent(getActivity(), JadwalSholatActivity.class);
                    startActivity(i);
                } else if (ff.getFiturNama().equalsIgnoreCase("Kalender Hijriah")) {
                    Intent i = new Intent(getActivity(), CalendarActivity.class);
                    startActivity(i);
                } else if (ff.getFiturNama().equalsIgnoreCase("Jadwal Kajian")) {
                    Intent i = new Intent(getActivity(), JadwalKajianMainActivity.class);
                    startActivity(i);
                } else if (ff.getFiturNama().equalsIgnoreCase("Do'a Harian")) {
                    Intent i = new Intent(getActivity(), ListDoaActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    void setFiturNa() {
        for (int i = 0; i < listGambarFitur.length; i++) {
            FiturModel ff = new FiturModel();
            ff.setFiturGambar(listGambarFitur[i]);
            ff.setFiturNama(listNamaFitur[i]);

            listFitur.add(ff);
        }

        gvFitur.setAdapter(fiturAdapter);
    }

    void setGambarNa() {
        ArrayList<String> listIsian = new ArrayList<>();
        listIsian.add("Ahlan Wa Sahlan!");
        listIsian.add("Follow Us");
        listIsian.add("Jasa Design Gratis!");
        listIsian.add("Belajar Agama Islam");

        ArrayList<String> listUrl = new ArrayList<>();
        listUrl.add("http://webforimage.000webhostapp.com/img/1.jpeg");
        listUrl.add("http://webforimage.000webhostapp.com/img/2.jpeg");
        listUrl.add("http://webforimage.000webhostapp.com/img/3.jpeg");
        listUrl.add("http://webforimage.000webhostapp.com/img/4.jpeg");


        for (int i = 0; i < listIsian.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            final int indexNa = i;
            textSliderView
                    .description(listIsian.get(i))
                    .image(listUrl.get(i))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Uri uriNa;
                            if (indexNa == 0) {
                                uriNa = Uri.parse("https://www.alfirqotunnajiyah.com/");
                                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriNa);
                                startActivity(launchBrowser);
                            } else if (indexNa == 1) {
                                uriNa = Uri.parse("https://www.alfirqotunnajiyah.com/fitur-gratis/");
                                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriNa);
                                startActivity(launchBrowser);
                            } else if (indexNa == 2) {
                                uriNa = Uri.parse("https://www.alfirqotunnajiyah.com/fitur-gratis/");
                                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriNa);
                                startActivity(launchBrowser);
                            } else {
                                uriNa = Uri.parse("http://www.alfirqotunnajiyah.com/belajar-islam/");
                                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriNa);
                                startActivity(launchBrowser);
                            }

                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", listUrl.get(i));

            slider.addSlider(textSliderView);
        }
//        slider.setCustomIndicator(pagerIndicator);
//        slider.setIndicatorVisibility(null);
        slider.setDuration(4000);
    }
}
