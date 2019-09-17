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

    private ListView listView;
    private ListAdapter adapter;
    private SwipeRefreshLayout srl;
    private ArrayList<String> textPuasa = new ArrayList<String>();
    private ArrayList<Integer> colorPuasa = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        HashSet<Date> events = new HashSet<>();

        srl = findViewById(R.id.srl);
        srl.setEnabled(false);

        listView = (ListView)findViewById(R.id.lvPuasa);
        adapter = new ListAdapter(this, textPuasa, colorPuasa);
        listView.setAdapter(adapter);

        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(CalendarActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDayPress(int color, String text) {
                if (!text.isEmpty())
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void passData(int color, String text) {
                textPuasa.add(text);
                colorPuasa.add(color);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void deleteData() {
                textPuasa.clear();
                colorPuasa.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}
