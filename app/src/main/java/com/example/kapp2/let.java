package com.example.kapp2;

import android.media.MediaPlayer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class let extends AppCompatActivity {
    public void CuantaActitud(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.cuenta_actitud);
    }
}
