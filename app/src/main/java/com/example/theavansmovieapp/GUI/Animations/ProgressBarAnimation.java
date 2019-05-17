package com.example.theavansmovieapp.GUI.Animations;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theavansmovieapp.GUI.Activity.HomeActivity;

/** gemaakt door luc */
public class ProgressBarAnimation extends Animation {

    /** klas attributen */
    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private float from;
    private float to;

    /** constructor */
    public ProgressBarAnimation(Context context, ProgressBar progressBar, TextView textView, float from, float to) {
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
    }

    /** animatie */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
        textView.setText((int) value + " %");

        if (value == to) {
            Intent goToHomeScreen = new Intent(context, HomeActivity.class);
            context.startActivity(goToHomeScreen);
            Toast.makeText(context, "App is loaded!", Toast.LENGTH_LONG).show();
        }
    }
}
