package com.afn.afnapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.FiturModel;

import java.util.ArrayList;
import java.util.List;

public class FiturAdapter extends ArrayAdapter<FiturModel> {
    private Activity context;
    private List<FiturModel> listFitur = new ArrayList<>();
    private View view;

    public FiturAdapter(Activity context, List<FiturModel> model) {
        super(context, 0, model);
        this.context = context;
        this.listFitur = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.list_fitur_afn, parent, false);

        ImageView ivFitur = view.findViewById(R.id.ivFitur);
        TextView tvFitur = view.findViewById(R.id.tvFitur);

        FiturModel fm = listFitur.get(position);
        tvFitur.setText(fm.getFiturNama());
        ivFitur.setImageResource(fm.getFiturGambar());

        ivFitur.getLayoutParams().width = getPixelsFromDPs(context,168);

        return view;
    }

    // Method for converting DP value to pixels
    public static int getPixelsFromDPs(Activity activity, int dps){
        Resources r = activity.getResources();
        int  px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
    }
}
