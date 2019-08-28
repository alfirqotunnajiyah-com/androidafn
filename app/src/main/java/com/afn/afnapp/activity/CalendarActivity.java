package com.afn.afnapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.afn.afnapp.R;
import com.afn.afnapp.widget.CalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        HashSet<Date> events = new HashSet<>();

        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        cv.setEventHandler(new CalendarView.EventHandler()
        {
            @Override
            public void onDayLongPress(Date date)
            {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(CalendarActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
