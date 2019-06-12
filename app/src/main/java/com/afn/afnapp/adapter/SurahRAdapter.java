package com.afn.afnapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.afn.afnapp.activity.AlQuranFeature.IsiDariSurahActivity;
import com.afn.afnapp.model.SurahNameModel;

import java.util.List;

public class SurahRAdapter extends RecyclerView.Adapter<SurahRAdapter.MyViewHolder> {
    private List<SurahNameModel> mDataset;
    private Activity actNa;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvNamaSurah, tvNamaSurahIndo, tvNoSurah;
        public RelativeLayout rlMain;

        public MyViewHolder(View v) {
            super(v);
            tvNoSurah = v.findViewById(R.id.tvNoSurah);
            tvNamaSurah = v.findViewById(R.id.tvNamaSurah);
            tvNamaSurahIndo = v.findViewById(R.id.tvNamaSurahIndo);

            rlMain = v.findViewById(R.id.rlMain);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SurahRAdapter(Activity act, List<SurahNameModel> myDataset) {
        mDataset = myDataset;
        actNa = act;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SurahRAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_surah, parent, false);
        // create a new view
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final SurahNameModel snm = mDataset.get(position);
        if ((snm.getNoSurah() % 2) == 0) {
            holder.rlMain.setBackgroundColor(actNa.getResources().getColor(R.color.color1));
        } else {
            holder.rlMain.setBackgroundColor(actNa.getResources().getColor(R.color.color2));
        }
        holder.tvNoSurah.setText(snm.getNoSurah() + "");
        holder.tvNamaSurah.setText(snm.getNameSurah());
        holder.tvNamaSurahIndo.setText(snm.getNameSurahIndo());


        holder.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(actNa, IsiDariSurahActivity.class);
                i.putExtra("noSurah", snm.getNoSurah());
                i.putExtra("namaSurah", snm.getNameSurah());
                i.putExtra("namaSurahIndo", snm.getNameSurahIndo());
                actNa.startActivity(i);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}