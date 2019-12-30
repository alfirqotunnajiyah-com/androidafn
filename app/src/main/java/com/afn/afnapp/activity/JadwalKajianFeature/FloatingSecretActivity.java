package com.afn.afnapp.activity.JadwalKajianFeature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afn.afnapp.R;

public class FloatingSecretActivity extends AppCompatActivity {

    private EditText etSecret;
    private TextView btnSubmit;
    private RelativeLayout bgLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_secret);
        initView();
        initClick();
    }

    private void initView() {
        etSecret = (EditText) findViewById(R.id.etSecret);
        btnSubmit = (TextView) findViewById(R.id.btnSubmit);
        bgLinear = (RelativeLayout) findViewById(R.id.bgLinear);
    }

    private void initClick() {
        bgLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSecret.getText().length() > 0) {
                    if (etSecret.getText().toString().equalsIgnoreCase("afn_pass_132")) {
                        startActivity(new Intent(FloatingSecretActivity.this, ApproveListKajianActivity.class));
                        finish();
                    } else {
                        finish();
                    }
                }
            }
        });
    }
}
