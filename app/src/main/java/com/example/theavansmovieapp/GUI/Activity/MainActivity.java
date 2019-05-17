package com.example.theavansmovieapp.GUI.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.theavansmovieapp.GUI.Animations.ProgressBarAnimation;
import com.example.theavansmovieapp.R;

/** gemaakt door luc */

public class MainActivity extends AppCompatActivity {

    /** klas attributen */
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** ophalen van scherm omvang zodat er een fullscreen gezet kan worden */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /** de attributen koppelen aan de views uit de resources */
        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.text_view);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }

    /** een animatie methode  voor de progressbar om van 0 naar 100 te gaan. */
    public void progressAnimation() {
        ProgressBarAnimation anim = new ProgressBarAnimation(MainActivity.this, progressBar, textView, 0f, 100f);
        anim.setDuration(8000);
        progressBar.setAnimation(anim);
    }
}