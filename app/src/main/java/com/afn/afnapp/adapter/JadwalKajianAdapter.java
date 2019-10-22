package com.afn.afnapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.JadwalKajianModel;

import java.util.List;

public class JadwalKajianAdapter extends ArrayAdapter<JadwalKajianModel> {
    private Activity context;
    private List<JadwalKajianModel> listNa;
    private View view;

    public JadwalKajianAdapter(Activity context, List<JadwalKajianModel> model) {
        super(context, 0, model);
        this.context = context;
        this.listNa = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.list_kajian, parent, false);

        JadwalKajianModel mm = listNa.get(position);


        TextView tvJudulKajian = (TextView)view.findViewById(R.id.tvJudulKajian);
        tvJudulKajian.setText(mm.getJudulKajian());

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
