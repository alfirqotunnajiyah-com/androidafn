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
import android.widget.ImageView;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.AyahModel;
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
        ImageView ivGambar = view.findViewById(R.id.ivGambar);

        FeedModel fm = listAyah.get(position);

        ivGambar.setImageResource(fm.getIdResource());
        ivGambar.getLayoutParams().width = ss.getWidth()/2;
        ivGambar.getLayoutParams().height = ss.getWidth();

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
