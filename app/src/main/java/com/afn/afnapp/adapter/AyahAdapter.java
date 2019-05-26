package com.afn.afnapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.AyahModel;
import com.afn.afnapp.model.SurahNameModel;

import java.util.ArrayList;
import java.util.List;

public class AyahAdapter extends ArrayAdapter<AyahModel> {
    private Activity context;
    private List<AyahModel> listAyah = new ArrayList<>();
    private View view;

    public AyahAdapter(Activity context, List<AyahModel> model) {
        super(context, 0, model);
        this.context = context;
        this.listAyah = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.list_ayah, parent, false);

        TextView tvNoAyah = view.findViewById(R.id.tvNoAyah);
        TextView tvIsiAyah = view.findViewById(R.id.tvIsiAyah);
        TextView tvArtiAyah = view.findViewById(R.id.tvArtiAyah);

        AyahModel fm = listAyah.get(position);
        tvNoAyah.setText(fm.getNoAyah() + "");
        tvIsiAyah.setText((fm.getAyah()));
        tvArtiAyah.setText(Html.fromHtml(fm.getAyahTranslate()));

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
