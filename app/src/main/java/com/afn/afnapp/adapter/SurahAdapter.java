package com.afn.afnapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.SurahNameModel;

import java.util.ArrayList;
import java.util.List;

public class SurahAdapter extends ArrayAdapter<SurahNameModel> {
    private Activity context;
    private List<SurahNameModel> listSurahName = new ArrayList<>();
    private View view;

    public SurahAdapter(Activity context, List<SurahNameModel> model) {
        super(context, 0, model);
        this.context = context;
        this.listSurahName = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.list_surah, parent, false);

        TextView tvNoSurah = view.findViewById(R.id.tvNoSurah);
        TextView tvNameSurah = view.findViewById(R.id.tvNamaSurah);

        SurahNameModel fm = listSurahName.get(position);
        tvNoSurah.setText(fm.getNoSurah() + "");
        tvNameSurah.setText(fm.getNameSurah());

        return view;
    }

    // Method for converting DP value to pixels
    public static int getPixelsFromDPs(Activity activity, int dps) {
        Resources r = activity.getResources();
        int px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
    }
}
