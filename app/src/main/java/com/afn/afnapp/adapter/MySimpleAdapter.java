package com.afn.afnapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.model.SimpleModel;
import java.util.ArrayList;
import java.util.List;


public class MySimpleAdapter extends ArrayAdapter<SimpleModel> {
    private Activity context;
    private List<SimpleModel> listModel = new ArrayList<>();
    private View view;

    public MySimpleAdapter(Activity context, List<SimpleModel> model) {
        super(context, 0, model);
        this.context = context;
        this.listModel = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.list_simple, parent, false);

        SimpleModel simpleModel = listModel.get(position);

        TextView tvText = view.findViewById(R.id.tvText);
        tvText.setText(simpleModel.getNameSimple());

        return view;
    }
}
