package com.afn.afnapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.FiturModel;
import com.afn.afnapp.model.WilayahModel;

import java.util.ArrayList;
import java.util.List;

public class WilayahAdapter extends ArrayAdapter<WilayahModel> {
    private Activity context;
    private List<WilayahModel> listWilayah = new ArrayList<>();
    private View view;

    public WilayahAdapter(Activity context, List<WilayahModel> model) {
        super(context, 0, model);
        this.context = context;
        this.listWilayah = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.list_simple_wilayah, parent, false);

        WilayahModel wilayahModel = listWilayah.get(position);

        TextView tvText = view.findViewById(R.id.tvText);
        tvText.setText(wilayahModel.getNama());

        return view;
    }
}
