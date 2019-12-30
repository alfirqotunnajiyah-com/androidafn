package com.afn.afnapp.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afn.afnapp.R;
import com.afn.afnapp.activity.JadwalKajianFeature.JadwalKajianDetailActivity;
import com.afn.afnapp.model.JadwalKajianModel;
import com.afn.afnapp.utils.ApiUrl;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JadwalKajianApproveAdapter extends ArrayAdapter<JadwalKajianModel> {
    private Activity context;
    private List<JadwalKajianModel> listNa;
    private View view;

    private ApiUrl apiUrl;
    private RequestQueue queue;

    public JadwalKajianApproveAdapter(Activity context, List<JadwalKajianModel> model) {
        super(context, 0, model);
        this.context = context;
        this.listNa = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        view = inflater.inflate(R.layout.list_submited_kajian, parent, false);

        final ProgressDialog loading;
        final TextView tvTema;
        final TextView tvTempat;
        final ImageView btnInfo;
        final LinearLayout btnReject;
        final LinearLayout btnAccept;

        tvTema = (TextView) view.findViewById(R.id.tvTema);
        tvTempat = (TextView) view.findViewById(R.id.tvTempat);
        btnInfo = (ImageView) view.findViewById(R.id.btnInfo);
        btnReject = (LinearLayout) view.findViewById(R.id.btnReject);
        btnAccept = (LinearLayout) view.findViewById(R.id.btnAccept);

        apiUrl = new ApiUrl();
        queue = Volley.newRequestQueue(context);
        loading = new ProgressDialog(context);

        final JadwalKajianModel mm = listNa.get(position);

        tvTema.setText(mm.getTema());
        tvTempat.setText(mm.getTempat());
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, JadwalKajianDetailActivity.class);
                i.putExtra("idKajian", mm.getIdKajian());
                context.startActivity(i);
            }
        });

        final int idKajian = mm.getIdKajian();

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(idKajian, 22, btnAccept, btnReject,loading);
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(idKajian, 23, btnAccept, btnReject,loading);
            }
        });

        return view;
    }

    private void updateData(final int idKajian, final int valueInt, final LinearLayout btAcc, final LinearLayout btRj,final ProgressDialog loading) {
        String url = apiUrl.getMainUrl() + "get_data.php?mode=" + valueInt;
        //Log.d("isiResponse", url);
        // Request a string response from the provided URL.
        loading.setMessage("Mohon tunggu...");
        loading.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("isiResponse", response);
                        loading.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            String statusApi = obj.getString("afn_status");
                            if (statusApi.equalsIgnoreCase("success")) {
                                if (valueInt == 22) {
                                    btAcc.setVisibility(View.GONE);
                                    btRj.setVisibility(View.VISIBLE);
                                } else {
                                    btAcc.setVisibility(View.VISIBLE);
                                    btRj.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException ex) {
                            //ex.printStackTrace();
                            Snackbar.make(btAcc, "Gagal mengambil data", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                //Log.d("isiResponse", error.getMessage());
                //error.printStackTrace();
                //Toast.makeText(SubmitNewKajianActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Snackbar.make(btAcc, "Tidak terhubung ke internet", Snackbar.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_kajian", idKajian + "");
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    // Method for converting DP value to pixels
    public static int getPixelsFromDPs(Activity activity, int dps) {
        Resources r = activity.getResources();
        int px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
    }
}
