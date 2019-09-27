package com.afn.afnapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afn.afnapp.R;
import com.afn.afnapp.utils.Constans;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class CalendarView extends LinearLayout {
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

    // today
    Date today = new Date();

    //event handling
    private EventHandler eventHandler = null;

    // internal components
    private LinearLayout header;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDateGregorian;
    private TextView txtDate;
    private GridView grid;

    Chronology iso = ISOChronology.getInstanceUTC();
    Chronology hijri = IslamicChronology.getInstance(DateTimeZone.getDefault(), IslamicChronology.LEAP_YEAR_16_BASED);

    private String[] sMonth = {"Muharram", "Safar", "Rabiul awal", "Rabiul akhir", "Jumadil awal", "Jumadil akhir", "Rajab", "Sya'ban", "Ramadhan", "Syawal", "Dzulkaidah", "Dzulhijjah"};
    private String[] sMonthGregorian = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
    private ArrayList<String> textPuasa = new ArrayList<>();
    private ArrayList<Integer> colorPuasa = new ArrayList<>();
    private boolean senin = false, kamis = false;
    private Drawable wrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);
    private Drawable wrappedDrawable1 = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);
    private Drawable wrappedDrawable2 = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);
    private Drawable wrappedDrawable3 = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);
    private Drawable wrappedDrawable4 = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);
    private Drawable wrappedDrawable5 = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);

    private String tmp;

    private int iMonth = currentDate.get(Calendar.MONTH);

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    /**
     * Load control xml layout
     */
    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control_calendar, this);

        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();

        updateCalendar();
    }

    private void loadDateFormat(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        try {
            // try to load provided date format, and fallback to default otherwise
            dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
            if (dateFormat == null)
                dateFormat = DATE_FORMAT;
        } finally {
            ta.recycle();
        }
    }

    private void assignUiElements() {
        // layout is inflated, assign local variables to components
        header = findViewById(R.id.calendar_header);
        btnPrev = findViewById(R.id.calendar_prev_button);
        btnNext = findViewById(R.id.calendar_next_button);
        txtDateGregorian = findViewById(R.id.calendar_date_display_gregorian);
        txtDate = findViewById(R.id.calendar_date_display);
        grid = findViewById(R.id.calendar_grid);
    }

    private void assignClickHandlers() {
        // add one month and refresh UI
        btnNext.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                eventHandler.deleteData();
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
                    eventHandler.deleteData();
                    currentDate.add(Calendar.MONTH, -1);
                    iMonth--;
                    updateCalendar();
                } else
                    Toast.makeText(getContext(), "Anda sudah mencapai batas awal", Toast.LENGTH_SHORT).show();
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

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // handle press
                Date date = (Date) parent.getItemAtPosition(position);
                int month = date.getMonth();
                LocalDate todayIso = new LocalDate(LocalDate.fromDateFields(date), iso);
                LocalDate todayHijri = new LocalDate(todayIso.toDateTimeAtStartOfDay(), hijri);

                // pewarnaan puasa -- sort by priority
                if (month == iMonth % 12) {
                    if ((todayHijri.getMonthOfYear() == 10 && todayHijri.getDayOfMonth() == 1) ||
                            (todayHijri.getMonthOfYear() == 12 && todayHijri.getDayOfMonth() == 10) ||
                                (todayHijri.getMonthOfYear() == 12 && (todayHijri.getDayOfMonth() >= 11 && todayHijri.getDayOfMonth() <= 13))) {
                        eventHandler.onDayPress(Constans.PUASA_DILARANG);
                    } else if (todayHijri.getMonthOfYear() == 10 && (todayHijri.getDayOfMonth() >= 2 && todayHijri.getDayOfMonth() <= 7)) {
                        eventHandler.onDayPress(Constans.PUASA_SYAWWAL);
                    } else if (todayHijri.getMonthOfYear() == 9) {
                        eventHandler.onDayPress(Constans.PUASA_RAMADHAN);
                    } else if (todayHijri.getMonthOfYear() == 12 && todayHijri.getDayOfMonth() == 9) {
                        eventHandler.onDayPress(Constans.PUASA_ARAFAH);
                    } else if ((todayHijri.getDayOfMonth() >= 13 && todayHijri.getDayOfMonth() <= 15) ||
                            (todayHijri.getMonthOfYear() == 12 && (todayHijri.getDayOfMonth() >= 14 && todayHijri.getDayOfMonth() <= 16))) {
                        eventHandler.onDayPress(Constans.PUASA_AYYAMUL_BIDH);
                    } else if (date.getDay() == 1 || date.getDay() == 4) {
                        eventHandler.onDayPress(Constans.PUASA_SENIN_KAMIS);
                    } else {
                        eventHandler.onDayPress("");
                    }
                }
            }
        });
    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar() {
        updateCalendar(null);
    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar(HashSet<Date> events) {
        // reset title
        tmp = "";

        // reset puasa
        textPuasa.clear();
        colorPuasa.clear();
        senin = kamis = false;

        // change header title
        if (today.toString().contains(String.valueOf(currentDate.get(Calendar.YEAR))))
            txtDateGregorian.setText(sMonthGregorian[currentDate.get(Calendar.MONTH)]);
        else
            txtDateGregorian.setText(sMonthGregorian[currentDate.get(Calendar.MONTH)] + " " + currentDate.get(Calendar.YEAR));

        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar)currentDate.clone();

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        // fill cells
        while (cells.size() < DAYS_COUNT) {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // update grid
        grid.setAdapter(new CalendarAdapter(getContext(), cells, events));
    }

    private class CalendarAdapter extends ArrayAdapter<Date> {
        // days with events
        private HashSet<Date> eventDays;

        // for view inflation
        private LayoutInflater inflater;

        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays) {
            super(context, R.layout.control_calendar_day, days);
            this.eventDays = eventDays;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            Drawable unwrappedDrawable = null;
            wrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);
            wrappedDrawable1 = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);
            wrappedDrawable2 = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);
            wrappedDrawable3 = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);
            wrappedDrawable4 = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);
            wrappedDrawable5 = AppCompatResources.getDrawable(getContext(), R.drawable.kosong);

            // day in question
            Date date = getItem(position);
            int day = date.getDate();
            int month = date.getMonth();
            int year = date.getYear();

            // get last day of month
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);

            // inflate item if it does not exist yet
            if (view == null)
                view = inflater.inflate(R.layout.control_calendar_day, null, false);

            // if this day has an event, specify event image
            view.setBackgroundResource(0);
            if (eventDays != null) {
                for (Date eventDate : eventDays) {
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
                if ((todayHijri.getMonthOfYear() == 10 && todayHijri.getDayOfMonth() == 1) ||
                        (todayHijri.getMonthOfYear() == 12 && todayHijri.getDayOfMonth() == 10) ||
                        (todayHijri.getMonthOfYear() == 12 && (todayHijri.getDayOfMonth() >= 11 && todayHijri.getDayOfMonth() <= 13))) {
                    unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_circle);
                    wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, getContext().getResources().getColor(R.color.dilarang));
                    view.setBackground(wrappedDrawable);

                    if (!colorPuasa.contains(R.color.dilarang)) {
                        colorPuasa.add(R.color.dilarang);
                        eventHandler.passData(R.color.dilarang, Constans.PUASA_DILARANG, Constans.PUASA_DILARANG_INFO);
                    }
                } else if (todayHijri.getMonthOfYear() == 9) {
                    if (todayHijri.getDayOfMonth() == 1) {
                        unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_left);
                    } else if (todayHijri.plusDays(1).getDayOfMonth() == 1) {
                        unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_right);
                    } else {
                        unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_square_hor);
                    }

                    wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
                    DrawableCompat.setTint(wrappedDrawable, getContext().getResources().getColor(R.color.ramadhan));
                    view.setBackground(wrappedDrawable);

                    if (!colorPuasa.contains(R.color.ramadhan)) {
                        colorPuasa.add(R.color.ramadhan);
                        eventHandler.passData(R.color.ramadhan, Constans.PUASA_RAMADHAN, Constans.PUASA_RAMADHAN_INFO);
                    }
                } else {
                    if (date.getDay() == 1) { // puasa senin
                        if (lastDay - day <= 6) {
                            if (senin)
                                unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_bot);
                            else
                                unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_circle);
                        } else if (senin) {
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_square_ver);
                        } else {
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_top);
                            senin = true;
                        }

                        wrappedDrawable5 = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable5, getContext().getResources().getColor(R.color.senin_kamis));

                        if (!colorPuasa.contains(R.color.senin_kamis)) {
                            colorPuasa.add(R.color.senin_kamis);
                            eventHandler.passData(R.color.senin_kamis, Constans.PUASA_SENIN_KAMIS, Constans.PUASA_SENIN_KAMIS_INFO);
                        }
                    } if (date.getDay() == 4) { // puasa kamis
                        if (lastDay - day <= 6) {
                            if (kamis)
                                unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_bot);
                            else
                                unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_circle);
                        } else if (kamis) {
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_square_ver);
                        } else {
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_top);
                            kamis = true;
                        }

                        wrappedDrawable5 = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable5, getContext().getResources().getColor(R.color.senin_kamis));

                        if (!colorPuasa.contains(R.color.senin_kamis)) {
                            colorPuasa.add(R.color.senin_kamis);
                            eventHandler.passData(R.color.senin_kamis, Constans.PUASA_SENIN_KAMIS, Constans.PUASA_SENIN_KAMIS_INFO);
                        }
                    } if ((todayHijri.getMonthOfYear() != 12 && (todayHijri.getDayOfMonth() >= 13 && todayHijri.getDayOfMonth() <= 15))) { // puasa ayyamul bidh
                        if (todayHijri.getDayOfMonth() == 13)
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_left);
                        else if (todayHijri.getDayOfMonth() == 15)
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_right);
                        else
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_square_hor);

                        wrappedDrawable4 = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable4, getContext().getResources().getColor(R.color.ayyamul_bidh));

                        if (!colorPuasa.contains(R.color.ayyamul_bidh)) {
                            colorPuasa.add(R.color.ayyamul_bidh);
                            eventHandler.passData(R.color.ayyamul_bidh, Constans.PUASA_AYYAMUL_BIDH, Constans.PUASA_AYYAMUL_BIDH_INFO);
                        }
                    } if ((todayHijri.getMonthOfYear() == 12 && (todayHijri.getDayOfMonth() >= 14 && todayHijri.getDayOfMonth() <= 16))) { // puasa ayyamul bidh
                        if (todayHijri.getDayOfMonth() == 14)
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_left);
                        else if (todayHijri.getDayOfMonth() == 16)
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_right);
                        else
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_square_hor);

                        wrappedDrawable3 = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable3, getContext().getResources().getColor(R.color.ayyamul_bidh));

                        if (!colorPuasa.contains(R.color.ayyamul_bidh)) {
                            colorPuasa.add(R.color.ayyamul_bidh);
                            eventHandler.passData(R.color.ayyamul_bidh, Constans.PUASA_AYYAMUL_BIDH, Constans.PUASA_AYYAMUL_BIDH_INFO);
                        }
                    } if (todayHijri.getMonthOfYear() == 12 && todayHijri.getDayOfMonth() == 9) { // puasa arafah
                        unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_circle);
                        wrappedDrawable2 = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable2, getContext().getResources().getColor(R.color.arafah));

                        if (!colorPuasa.contains(R.color.arafah)) {
                            colorPuasa.add(R.color.arafah);
                            eventHandler.passData(R.color.arafah, Constans.PUASA_ARAFAH, Constans.PUASA_ARAFAH_INFO);
                        }
                    } if (todayHijri.getMonthOfYear() == 10 && (todayHijri.getDayOfMonth() >= 2 && todayHijri.getDayOfMonth() <= 7)) { // puasa syawwal
                        if (todayHijri.getDayOfMonth() == 2) {
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_left);
                        } else if (todayHijri.getDayOfMonth() == 7) {
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_right);
                        } else {
                            unwrappedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.shape_square_hor);
                        }

                        wrappedDrawable1 = DrawableCompat.wrap(unwrappedDrawable);
                        DrawableCompat.setTint(wrappedDrawable1, getContext().getResources().getColor(R.color.syawwal));

                        if (!colorPuasa.contains(R.color.syawwal)) {
                            colorPuasa.add(R.color.syawwal);
                            eventHandler.passData(R.color.syawwal, Constans.PUASA_SYAWWAL, Constans.PUASA_SYAWWAL_INFO);
                        }
                    }

                    Drawable[] layers = {wrappedDrawable5, wrappedDrawable4, wrappedDrawable3, wrappedDrawable2, wrappedDrawable1};
                    LayerDrawable layer = new LayerDrawable(layers);
                    view.setBackground(layer);
                }
            }

            if (month != iMonth % 12) {
                // if this day is outside current month, ngga usah di tampilin
                ((TextView)view).setText("");
                view.setBackgroundColor(0);
            }
            else if (day == today.getDate() && month == today.getMonth() && year == today.getYear()) {
                // if it is today, set it to blue/bold
                ((TextView)view).setTypeface(null, Typeface.BOLD);
                ((TextView)view).setTextColor(getResources().getColor(R.color.today));
            }

            if (date.getDay() == 0) ((TextView)view).setTextColor(getResources().getColor(R.color.ahad));

            if (month == iMonth % 12) {
                if (!tmp.contains(sMonth[todayHijri.getMonthOfYear()-1])) {
                    if (!tmp.isEmpty()) {
                        tmp = tmp.concat(" - " + sMonth[todayHijri.getMonthOfYear()-1] + " " + todayHijri.getYear());
                    } else {
                        tmp = tmp.concat(sMonth[todayHijri.getMonthOfYear()-1]);
                        if (todayHijri.getMonthOfYear() == 12)
                            tmp = tmp.concat(" " + todayHijri.getYear());
                    }
                }
            } else if (month == (iMonth % 12) + 1) {
                if (!tmp.contains(String.valueOf(todayHijri.minusDays(day).getYear())))
                    tmp = tmp.concat(" " + todayHijri.getYear());
                txtDate.setText(tmp);
            }

            return view;
        }
    }

    /**
     * Assign event handler to be passed needed events
     */
    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    /**
     * This interface defines what events to be reported to
     * the outside world
     */
    public interface EventHandler {
        void onDayLongPress(Date date);
        void onDayPress(String text);
        void passData(int color, String text, String info);
        void deleteData();
    }
}
