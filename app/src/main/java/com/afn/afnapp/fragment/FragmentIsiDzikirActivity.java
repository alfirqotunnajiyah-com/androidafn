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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIsiDzikirActivity extends Fragment {

    private View view;
    private android.widget.TextView tvText;
    private android.widget.TextView tvTitle;
    private android.widget.ImageView ivLeft;
    private android.widget.ImageView ivRight;
    private com.xw.repo.BubbleSeekBar sliderNa;

    public FragmentIsiDzikirActivity() {
        // Required empty public constructor
    }

    // newInstance constructor for creating fragment with arguments
    public static FragmentIsiDzikirActivity newInstance(String title, String page) {
        FragmentIsiDzikirActivity fragmentFirst = new FragmentIsiDzikirActivity();

        Bundle args = new Bundle();
        args.putString("titleNa", title);
        args.putString("textNa", page);
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

        return view;
    }

    void setLayout() {
        this.sliderNa = (BubbleSeekBar) view.findViewById(R.id.sliderNa);
        this.tvText = (TextView) view.findViewById(R.id.tvText);
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
                        progress
                );
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                tvText.setTextSize(
                        progress
                );
            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                tvText.setTextSize(
                        progress
                );
            }
        });
    }

}
