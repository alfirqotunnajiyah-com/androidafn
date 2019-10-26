package com.afn.afnapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.FeedModel;
import com.afn.afnapp.utils.ScreenSize;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends ArrayAdapter<FeedModel> {
    private Activity context;
    private List<FeedModel> listAyah = new ArrayList<>();
    private View view;
    private ScreenSize ss;

    public FeedAdapter(Activity context, List<FeedModel> model) {
        super(context, 0, model);
        this.context = context;
        this.listAyah = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.list_image_feed, parent, false);

        ss = new ScreenSize(context);

        TextView tvAkun = view.findViewById(R.id.tvAkun);
        TextView tvNamaAkun = view.findViewById(R.id.tvNamaAkun);

        CardView cvNa = view.findViewById(R.id.cvNa);
        ImageView ivGambar = view.findViewById(R.id.ivGambar);

        final FeedModel fm = listAyah.get(position);

        ivGambar.setImageResource(fm.getIdResource());
        ivGambar.getLayoutParams().width = ss.getWidth() / 9;
        ivGambar.getLayoutParams().height = ss.getWidth() / 9;

        tvNamaAkun.setText(fm.getTitle());
        tvAkun.setText(fm.getCaption());

        cvNa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(fm.getLinkImage()));
                context.startActivity(launchBrowser);
            }
        });


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
