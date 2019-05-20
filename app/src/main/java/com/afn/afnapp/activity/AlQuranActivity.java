package com.afn.afnapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.afn.afnapp.R;
import com.afn.afnapp.adapter.SurahAdapter;
import com.afn.afnapp.model.SurahNameModel;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class AlQuranActivity extends AppCompatActivity {

    private android.widget.Button btnKlik;
    private com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView lvSurah;

    private List<SurahNameModel> listSurah = new ArrayList<>();
    private SurahAdapter adapter;

    private String arrNamaSurah[] = {"سورة الفاتحة",
            "سورة البقرة",
            "سورة آل عمران",
            "سورة النساء",
            "سورة المائدة",
            "سورة الأنعام",
            "سورة الأعراف",
            "سورة الأنفال",
            "سورة التوبة",
            "سورة يونس",
            "سورة هود",
            "سورة يوسف",
            "سورة الرعد",
            "سورة إبراهيم",
            "سورة الحجر",
            "سورة النحل",
            "سورة الإسراء",
            "سورة الكهف",
            "سورة مريم",
            "سورة طه",
            "سورة الأنبياء",
            "سورة الحج",
            "سورة المؤمنون",
            "سورة النور",
            "سورة الفرقان",
            "سورة الشعراء",
            "سورة النمل",
            "سورة القصص",
            "سورة العنكبوت",
            "سورة الروم",
            "سورة لقمان",
            "سورة السجدة",
            "سورة الأحزاب",
            "سورة سبأ",
            "سورة فاطر",
            "سورة يس",
            "سورة الصافات",
            "سورة ص",
            "سورة الزمر",
            "سورة غافر",
            "سورة فصلت",
            "سورة الشورى",
            "سورة الزخرف",
            "سورة الدخان",
            "سورة الجاثية",
            "سورة الأحقاف",
            "سورة محمد",
            "سورة الفتح",
            "سورة الحجرات",
            "سورة ق",
            "سورة الذاريات",
            "سورة الطور",
            "سورة النجم",
            "سورة القمر",
            "سورة الرحمن",
            "سورة الواقعة",
            "سورة الحديد",
            "سورة المجادلة",
            "سورة الحشر",
            "سورة الممتحنة",
            "سورة الصف",
            "سورة الجمعة",
            "سورة المنافقون",
            "سورة التغابن",
            "سورة الطلاق",
            "سورة التحريم",
            "سورة الملك",
            "سورة القلم",
            "سورة الحاقة",
            "سورة المعارج",
            "سورة نوح",
            "سورة الجن",
            "سورة المزمل",
            "سورة المدثر",
            "سورة القيامة",
            "سورة الإنسان",
            "سورة المرسلات",
            "سورة النبأ",
            "سورة النازعات",
            "سورة عبس",
            "سورة التكوير",
            "سورة الإنفطار",
            "سورة المطففين",
            "سورة الانشقاق",
            "سورة البروج",
            "سورة الطارق",
            "سورة الأعلى",
            "سورة الغاشية",
            "سورة الفجر",
            "سورة البلد",
            "سورة الشمس",
            "سورة الليل",
            "سورة الضحى",
            "سورة الشرح",
            "سورة التين",
            "سورة العلق",
            "سورة القدر",
            "سورة البينة",
            "سورة الزلزلة",
            "سورة العاديات",
            "سورة القارعة",
            "سورة التكاثر",
            "سورة العصر",
            "سورة الهمزة",
            "سورة الفيل",
            "سورة قريش",
            "سورة الماعون",
            "سورة الكوثر",
            "سورة الكافرون",
            "سورة النصر",
            "سورة المسد",
            "سورة الإخلاص",
            "سورة الفلق",
            "سورة الناس"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_quran);

        adapter = new SurahAdapter(this, listSurah);
        adapter.notifyDataSetChanged();

        setLayout();
        setKlik();
        tampilkanNamaSurah();
    }


    void setLayout() {
        this.lvSurah = (ExpandableHeightListView) findViewById(R.id.lvSurah);
        this.btnKlik = (Button) findViewById(R.id.btnKlik);

        this.lvSurah.setExpanded(true);
    }

    void setKlik() {
        btnKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //readData();
            }
        });

        lvSurah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SurahNameModel sm = listSurah.get(position);
                Intent i = new Intent(AlQuranActivity.this,IsiDariSurahActivity.class);
                i.putExtra("noSurah",sm.getNoSurah());
                i.putExtra("namaSurah",sm.getNameSurah());
                startActivity(i);
            }
        });
    }

    void tampilkanNamaSurah() {
        listSurah.clear();
        for (int i = 0; i < arrNamaSurah.length; i++) {
            SurahNameModel ss = new SurahNameModel();
            ss.setNoSurah(i + 1);
            ss.setNameSurah(arrNamaSurah[i]);
            listSurah.add(ss);
        }
        lvSurah.setAdapter(adapter);
    }

    private void readData() {
        listSurah.clear();

        InputStream is1 = getResources().openRawResource(R.raw.arabic);
        InputStream is2 = getResources().openRawResource(R.raw.indonesian);
        BufferedReader reader1 = new BufferedReader(
                new InputStreamReader(is1, Charset.forName("UTF-8")));
        BufferedReader reader2 = new BufferedReader(
                new InputStreamReader(is2, Charset.forName("UTF-8")));
        String line1 = "";
        String line2 = "";

        try {
            while ((line1 = reader1.readLine()) != null && ((line2 = reader2.readLine()) != null)) {
                // Split the line into different tokens (using the comma as a separator).
                String[] tokensArabic = line1.split(";");
                String[] tokensIndonesia = line2.split(";");

                if (tokensArabic[1].equalsIgnoreCase("2")) {
                    SurahNameModel sm = new SurahNameModel();
                    sm.setNameSurah(tokensArabic[3] + "\n" + tokensIndonesia[3]);
                    sm.setNoSurah(Integer.parseInt(tokensArabic[1]));
                    listSurah.add(sm);
                }

                // Read the data and store it in the WellData POJO.
            }
        } catch (IOException e1) {
            Log.e("MainActivity", "Error" + line1, e1);
            Log.e("MainActivity", "Error" + line2, e1);
            e1.printStackTrace();
        }

        lvSurah.setAdapter(adapter);
    }
}
