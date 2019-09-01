package com.afn.afnapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.afn.afnapp.R;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class CalendarView extends LinearLayout
{
    // for logging
    private static final String LOGTAG = "Calendar View";

    // how many days to show, defaults to six weeks, 42 days
    private static final int DAYS_COUNT = 42;

    // default date format
    private static final String DATE_FORMAT = "MMM yyyy";

    // date format
    private String dateFormat;

    // current displayed month
    private Calendar currentDate = Calendar.getInstance();

    //event handling
    private EventHandler eventHandler = null;

    // internal components
    private LinearLayout header;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;
    private ListView listView;
    private ListAdapter adapter;

    Chronology iso = ISOChronology.getInstanceUTC();
    Chronology hijri = IslamicChronology.getInstance(DateTimeZone.getDefault(), IslamicChronology.LEAP_YEAR_16_BASED);

    private String[] sMonth = {"Muharram", "Safar", "Rabiul awal", "Rabiul akhir", "Jumadil awal", "Jumadil akhir", "Rajab", "Sya'ban", "Ramadhan", "Syawal", "Dzulkaidah", "Dzulhijjah"};
    private ArrayList<String> textPuasa = new ArrayList<String>();
    private ArrayList<Integer> colorPuasa = new ArrayList<Integer>();

    private String tmp = "";

    private int iMonth = currentDate.get(Calendar.MONTH);

    public CalendarView(Context context)
    {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    /**
     * Load control xml layout
     */
    private void initControl(Context context, AttributeSet attrs)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control_calendar, this);

        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();

        updateCalendar();
    }

    private void loadDateFormat(AttributeSet attrs)
    {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        try
        {
            // try to load provided date format, and fallback to default otherwise
            dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
            if (dateFormat == null)
                dateFormat = DATE_FORMAT;
        }
        finally
        {
            ta.recycle();
        }
    }

    private void assignUiElements()
    {
        // layout is inflated, assign local variables to components
        header = (LinearLayout)findViewById(R.id.calendar_header);
        btnPrev = (ImageView)findViewById(R.id.calendar_prev_button);
        btnNext = (ImageView)findViewById(R.id.calendar_next_button);
        txtDate = (TextView)findViewById(R.id.calendar_date_display);
        grid = (GridView)findViewById(R.id.calendar_grid);
        listView = (ListView)findViewById(R.id.lvPuasa);

        adapter = new ListAdapter(getContext(), textPuasa, colorPuasa);
        listView.setAdapter(adapter);
    }

    private void assignClickHandlers()
    {
        // add one month and refresh UI
        btnNext.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //reset puasa
                textPuasa.clear();
                colorPuasa.clear();

                currentDate.add(Calendar.MONTH, 1);
                iMonth++;
                updateCalendar();
            }
        });

        // subtract one month and refresh UI
        btnPrev.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (iMonth >= 1) {
                    //reset puasa
                    textPuasa.clear();
                    colorPuasa.clear();

                    currentDate.add(Calendar.MONTH, -1);
                    iMonth--;
                    updateCalendar();
                }
            }
        });

        // long-pressing a day
        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id)
            {
                // handle long-press
                if (eventHandler == null)
                    return false;

                eventHandler.onDayLongPress((Date)view.getItemAtPosition(position));
                return true;
            }
        });
    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar()
    {
        updateCalendar(null);
    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar(HashSet<Date> events)
    {
        //reset title
        tmp = "";

        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar)currentDate.clone();

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        // fill cells
        while (cells.size() < DAYS_COUNT)
        {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // update grid
        grid.setAdapter(new CalendarAdapter(getContext(), cells, events));
    }


    private class CalendarAdapter extends ArrayAdapter<Date>
    {
        // days with events
        private HashSet<Date> eventDays;

        // for view inflation
        private LayoutInflater inflater;

        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays)
        {
            super(context, R.layout.control_calendar_day, days);
            this.eventDays = eventDays;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            // day in question
            Date date = getItem(position);
            int day = date.getDate();
            int month = date.getMonth();
            int year = date.getYear();

            // today
            Date today = new Date();

            // inflate item if it does not exist yet
            if (view == null)
                view = inflater.inflate(R.layout.control_calendar_day, null, false);

            // if this day has an event, specify event image
            view.setBackgroundResource(0);
            if (eventDays != null)
            {
                for (Date eventDate : eventDays)
                {
                    if (eventDate.getDate() == day &&
                            eventDate.getMonth() == month &&
                            eventDate.getYear() == year)
                    {
                        // mark this day for event
                        view.setBackgroundResource(R.drawable.reminder);
                        break;
                    }
                }
            }

            // clear styling
            ((TextView)view).setTypeface(null, Typeface.NORMAL);
            //((TextView)view).setTextColor(Color.BLACK);
            ((TextView)view).setText("");

            // set text
            LocalDate todayIso = new LocalDate(LocalDate.fromDateFields(date), iso);
            LocalDate todayHijri = new LocalDate(todayIso.toDateTimeAtStartOfDay(), hijri);
            ((TextView)view).setText(String.valueOf(todayHijri.getDayOfMonth()));

            // pewarnaan puasa -- sort by priority
            if (month == iMonth % 12) {
                if (todayHijri.getMonthOfYear()-1 == 9 && todayHijri.getDayOfMonth() == 1) {
                    ((TextView)view).setBackgroundColor(getResources().getColor(R.color.dilarang));
                    if (!colorPuasa.contains(R.color.dilarang)) {
                        textPuasa.add("Hari yang dilarang Puasa");
                        colorPuasa.add(R.color.dilarang);
                    }
                } else if (todayHijri.getMonthOfYear()-1 == 9 && (todayHijri.getDayOfMonth() >= 2 && todayHijri.getDayOfMonth() <= 7)) {
                    ((TextView)view).setBackgroundColor(getResources().getColor(R.color.syawwal));
                    if (!colorPuasa.contains(R.color.syawwal)) {
                        textPuasa.add("Puasa 6 hari dibulan Syawwal");
                        colorPuasa.add(R.color.syawwal);
                    }
                } else if (todayHijri.getMonthOfYear()-1 == 8) {
                    ((TextView)view).setBackgroundColor(getResources().getColor(R.color.ramadhan));
                    if (!colorPuasa.contains(R.color.ramadhan)) {
                        textPuasa.add("Puasa Ramadhan");
                        colorPuasa.add(R.color.ramadhan);
                    }
                } else if (todayHijri.getDayOfMonth() >= 13 && todayHijri.getDayOfMonth() <= 15) {
                    ((TextView)view).setBackgroundColor(getResources().getColor(R.color.ayyamul_bidh));
                    if (!colorPuasa.contains(R.color.ayyamul_bidh)) {
                        textPuasa.add("Puasa Ayyamul Bidh");
                        colorPuasa.add(R.color.ayyamul_bidh);
                    }
                } else if (date.getDay() == 1 || date.getDay() == 4) {
                    ((TextView)view).setBackgroundColor(getResources().getColor(R.color.senin_kamis));
                    if (!colorPuasa.contains(R.color.senin_kamis)) {
                        textPuasa.add("Puasa Senin dan Kamis");
                        colorPuasa.add(R.color.senin_kamis);
                    }
                }
            }

            adapter.notifyDataSetChanged();

            if (month != iMonth % 12)
            {
                // if this day is outside current month, ngga usah di tampilin
                ((TextView)view).setText("");
                ((TextView)view).setBackgroundColor(0);
            }
            else if (day == today.getDate() && month == today.getMonth() && year == today.getYear())
            {
                // if it is today, set it to blue/bold
                ((TextView)view).setTypeface(null, Typeface.BOLD);
                ((TextView)view).setTextColor(getResources().getColor(R.color.today));
            }

            if (date.getDay() == 0) ((TextView)view).setTextColor(getResources().getColor(R.color.ahad));

            //todo betulin bulan hijriahnya
            if (month == iMonth % 12) {
                if (!tmp.contains(sMonth[todayHijri.getMonthOfYear()-1]))
                    tmp = tmp.concat(sMonth[todayHijri.getMonthOfYear()-1] + " " + todayHijri.getYear());
                if (!tmp.contains("-"))
                    tmp = tmp.concat(" -\n");
                if (!tmp.contains(sMonth[todayHijri.getMonthOfYear()-1]))
                    tmp = tmp.concat(sMonth[todayHijri.getMonthOfYear()-1] + " " + todayHijri.getYear());
            }
            txtDate.setText(tmp);

            return view;
        }
    }

    /**
     * Assign event handler to be passed needed events
     */
    public void setEventHandler(EventHandler eventHandler)
    {
        this.eventHandler = eventHandler;
    }

    /**
     * This interface defines what events to be reported to
     * the outside world
     */
    public interface EventHandler
    {
        void onDayLongPress(Date date);
    }
}
