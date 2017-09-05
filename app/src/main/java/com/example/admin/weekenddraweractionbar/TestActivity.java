package com.example.admin.weekenddraweractionbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.RatingBar;
import android.widget.TextClock;
import android.widget.TextView;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

public class TestActivity extends AppCompatActivity {

    TextClock textClock;
    private TextView textView;
    private RatingBar rbStars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = (TextView) findViewById(R.id.tvStars);
        textClock = (TextClock) findViewById(R.id.tcTime);
        rbStars = (RatingBar) findViewById(R.id.rbStars);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                textView.setText(""+v);
            }
        });


//        int w = textClock.getWidth();
//        int h = textClock.getHeight();
//
//        textClock.setTextSize(COMPLEX_UNIT_SP,h);

//        Log.d("TAG", "onCreate: " + w);
    }
}
