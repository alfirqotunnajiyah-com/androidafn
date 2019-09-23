package com.afn.afnapp.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.afn.afnapp.R;
import com.afn.afnapp.widget.CalendarView;
import com.afn.afnapp.widget.ListAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class CalendarActivity extends AppCompatActivity {

    private ListAdapter adapter;
    private ArrayList<String> textPuasa = new ArrayList<>();
    private ArrayList<Integer> colorPuasa = new ArrayList<>();
    private ArrayList<String> infoPuasa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        HashSet<Date> events = new HashSet<>();

        SwipeRefreshLayout srl = findViewById(R.id.srl);
        srl.setEnabled(false);

        ListView listView = findViewById(R.id.lvPuasa);
        adapter = new ListAdapter(this, textPuasa, colorPuasa, infoPuasa);
        listView.setAdapter(adapter);

        textPuasa.add("Setiap hari Jum'at baca Al Kahfi Yuk!");
        colorPuasa.add(R.color.white);
        infoPuasa.add("HR. AnNasa'i dan Baihaqi");
        adapter.notifyDataSetChanged();

        CalendarView cv = findViewById(R.id.calendar_view);
        cv.updateCalendar(events);

        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(CalendarActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDayPress(String text) {
                if (!text.isEmpty())
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void passData(int color, String text, String info) {
                textPuasa.add(text);
                colorPuasa.add(color);
                infoPuasa.add(info);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void deleteData() {
                textPuasa.clear();
                colorPuasa.clear();
                infoPuasa.clear();
                textPuasa.add("Setiap hari Jum'at baca Al Kahfi Yuk!");
                colorPuasa.add(R.color.white);
                infoPuasa.add("HR. AnNasa'i dan Baihaqi");
                adapter.notifyDataSetChanged();
            }
        });
    }
}
