package com.example.moodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String activity_number;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.example.moodtracker", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("activity_number", "1");
        editor.commit();
        activity_number = sharedPreferences.getString("activity_number", "1");
        Log.i("num", activity_number);
        register = findViewById(R.id.button);

        if(Integer.parseInt(activity_number) == 2){
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
        }
        if(Integer.parseInt(activity_number) == 3){
            Intent inten = new Intent(this, Feeling.class);
            startActivity(inten);
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("activity_number", "2");
                editor.commit();
                Intent i = new Intent(getApplicationContext(), Signup.class);
                startActivity(i);
            }
        });
    }
}
