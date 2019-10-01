package com.afn.afnapp.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.afn.afnapp.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> content;
    private final ArrayList<Integer> color;
    private final ArrayList<String> info;

    public ListAdapter(Context context, ArrayList<String> content, ArrayList<Integer> color, ArrayList<String> info) {
        super(context, R.layout.item_puasa, content);

        this.context = context;
        this.content = content;
        this.color = color;
        this.info = info;
    }

    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.item_puasa, null,true);

        TextView contentText = rowView.findViewById(R.id.textView);
        TextView infoText = rowView.findViewById(R.id.textView2);

        contentText.setText(content.get(position));
        rowView.setBackgroundColor(context.getResources().getColor(color.get(position)));
        infoText.setText(info.get(position));

        return rowView;
    }
}
