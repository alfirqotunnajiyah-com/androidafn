package com.afn.afnapp.activity;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afn.afnapp.R;

import java.util.List;

public class ArahKiblat extends AppCompatActivity implements SensorEventListener {

    // define the display assembly compass picture
    private ImageView image;
    private TextView derajat;

    // record the compass picture angle turned
    private float currentDegree = 0f;

    // device sensor manager
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arah_kiblat);

        // our compass image
        image = (ImageView) findViewById(R.id.kompas);
        derajat = (TextView) findViewById(R.id.tvDerajat);

        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> isSupport = mSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        Log.d("supportHenteuna",isSupport.toString()+"   <<<<< ");
        if (isSupport.size() < 1){
            Toast.makeText(this, "Perangkat tidak support Sensor Kompas", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);

        derajat.setText("Derajat " + degree + "°");

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // how long the animation will take place
        ra.setDuration(60);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree+(294);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
