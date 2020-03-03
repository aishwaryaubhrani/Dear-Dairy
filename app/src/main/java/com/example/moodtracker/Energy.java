package com.example.moodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.sdsmdg.harjot.crollerTest.Croller;

public class Energy extends AppCompatActivity {
    TextView energy_level;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);
        sharedPreferences = this.getSharedPreferences("com.example.moodtracker", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Croller croller = (Croller) findViewById(R.id.croller);
        croller.setLabel(" ");
        energy_level = findViewById(R.id.textView3);
        croller.setOnProgressChangedListener(new Croller.onProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress) {
                // use the progress
                energy_level.setText(Integer.toString(progress));
            }
        });

        editor.putString("energy_level", energy_level.getText().toString());
        editor.commit();
    }
}
