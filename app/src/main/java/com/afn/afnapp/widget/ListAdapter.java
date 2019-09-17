package com.afn.afnapp.widget;

import android.content.Context;
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

    public ListAdapter(Context context, ArrayList<String> content, ArrayList<Integer> color) {
        super(context, R.layout.item_puasa, content);

        this.context = context;
        this.content = content;
        this.color = color;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.item_puasa, null,true);

        TextView contentText = (TextView) rowView.findViewById(R.id.textView);

        contentText.setText(content.get(position));
        rowView.setBackgroundColor(context.getResources().getColor(color.get(position)));

        return rowView;
    }
}
