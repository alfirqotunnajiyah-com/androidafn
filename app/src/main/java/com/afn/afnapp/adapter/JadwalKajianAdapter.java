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
import com.afn.afnapp.model.JadwalKajianModel;

import java.util.List;

public class JadwalKajianAdapter extends ArrayAdapter<JadwalKajianModel> {
    private Activity context;
    private List<JadwalKajianModel> listNa;
    private View view;
    private ImageView ivImg;
    private TextView tvKajianUntuk;
    private TextView tvTema;
    private TextView tvTempat;
    private TextView tvAlamatTempat;
    private TextView tvWaktu;

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

        initView();

        tvTema.setText(mm.getTema());
        tvTempat.setText(mm.getTempat());
        tvAlamatTempat.setText(mm.getAlamat());
        tvWaktu.setText(mm.getWaktu());
        if (mm.getIsKhusus() == 1) {
            tvKajianUntuk.setBackgroundResource(R.drawable.bg_lbl_ikhwan);
            tvKajianUntuk.setText("Umum");
        }else if(mm.getIsKhusus() == 2){
            tvKajianUntuk.setBackgroundResource(R.drawable.bg_lbl_ikhwan);
            tvKajianUntuk.setText("Ikhwan");
        }else{
            tvKajianUntuk.setBackgroundResource(R.drawable.bg_lbl_akhwat);
            tvKajianUntuk.setText("Akhwat");
        }

        return view;
    }

    // Method for converting DP value to pixels
    public static int getPixelsFromDPs(Activity activity, int dps) {
        Resources r = activity.getResources();
        int px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
    }

    private void initView() {
        tvTema = (TextView) view.findViewById(R.id.tvTema);
        tvTempat = (TextView) view.findViewById(R.id.tvTempat);
        tvAlamatTempat = (TextView) view.findViewById(R.id.tvAlamatTempat);
        tvWaktu = (TextView) view.findViewById(R.id.tvWaktu);
        tvKajianUntuk = (TextView) view.findViewById(R.id.tvKajianUntuk);
        ivImg = (ImageView) view.findViewById(R.id.ivImg);
    }
}
