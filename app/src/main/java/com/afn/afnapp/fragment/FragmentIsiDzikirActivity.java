package com.afn.afnapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afn.afnapp.R;
import com.xw.repo.BubbleSeekBar;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIsiDzikirActivity extends Fragment {

    private View view;
    private android.widget.TextView tvText;
    private android.widget.TextView tvTextTerjemahan;
    private android.widget.TextView tvTitle;
    private android.widget.TextView tvTextFaidah;
    private android.widget.ImageView ivLeft;
    private android.widget.ImageView ivRight;
    private com.xw.repo.BubbleSeekBar sliderNa;

    public FragmentIsiDzikirActivity() {
        // Required empty public constructor
    }

    // newInstance constructor for creating fragment with arguments
    public static FragmentIsiDzikirActivity newInstance(String title, String page, String arti, String faidah) {
        FragmentIsiDzikirActivity fragmentFirst = new FragmentIsiDzikirActivity();

        Bundle args = new Bundle();
        args.putString("titleNa", title);
        args.putString("textNa", page);
        args.putString("artiNa", arti);
        args.putString("faidahDanDalil", faidah);
        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_isi_dzikir_activity, container, false);

        setLayout();
        setKlik();

        tvTitle.setText(getArguments().getString("titleNa"));
        tvText.setText(getArguments().getString("textNa"));
        tvTextTerjemahan.setText(getArguments().getString("artiNa"));
        tvTextFaidah.setText(getArguments().getString("faidahDanDalil"));

        return view;
    }

    void setLayout() {
        this.sliderNa = (BubbleSeekBar) view.findViewById(R.id.sliderNa);
        this.tvText = (TextView) view.findViewById(R.id.tvText);
        this.tvTextTerjemahan = (TextView) view.findViewById(R.id.tvTextTerjemahan);
        this.tvTextFaidah = (TextView)view.findViewById(R.id.tvTextFaidah);
        this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        this.ivLeft = (ImageView) view.findViewById(R.id.ivLeft);
        this.ivRight = (ImageView) view.findViewById(R.id.ivRight);

        this.tvTitle.setSelected(true);
    }

    void setKlik() {
        sliderNa.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                tvText.setTextSize(
                        progressFloat
                );
                tvTextTerjemahan.setTextSize(progressFloat - 10);
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                tvText.setTextSize(
                        progressFloat
                );
                tvTextTerjemahan.setTextSize(progressFloat - 10);
            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                tvText.setTextSize(
                        progressFloat
                );
                tvTextTerjemahan.setTextSize(progressFloat - 10);
            }
        });
    }

}
