package com.example.moodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class Feeling extends AppCompatActivity {
    EditText date_picker, time_picker;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    ImageView menu_icon, rad, good, meh, bad;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String activity_number, mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling);
        sharedPreferences = this.getSharedPreferences("com.example.periodtracker", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        date_picker = findViewById(R.id.editText5);
        time_picker = findViewById(R.id.editText6);
        menu_icon = findViewById(R.id.imageView5);
        rad = findViewById(R.id.imageView);
        good = findViewById(R.id.imageView2);
        meh = findViewById(R.id.imageView3);
        bad = findViewById(R.id.imageView4);
        editor.putString("activity_number", "3");
        editor.commit();
        activity_number = sharedPreferences.getString("activity_number", "1");
        Log.i("num_shared", activity_number);

        date_picker.setInputType(InputType.TYPE_NULL);
        time_picker.setInputType(InputType.TYPE_NULL);

        Calendar calendar = Calendar.getInstance();
        int this_day = calendar.get(Calendar.DAY_OF_MONTH);
        int this_month = calendar.get(Calendar.MONTH);
        int this_year = calendar.get(Calendar.YEAR);
        int this_hour = calendar.get(Calendar.HOUR_OF_DAY);
        int this_minute = calendar.get(Calendar.MINUTE);

        date_picker.setText(Integer.toString(this_day)+"/"+Integer.toString(this_month)+"/"+Integer.toString(this_year));
        time_picker.setText(Integer.toString(this_hour)+":"+Integer.toString(this_minute));
        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(Feeling.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date_picker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                timePickerDialog =  new TimePickerDialog(Feeling.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                time_picker.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePickerDialog.show();
            }
        });

        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Feeling.this, menu_icon);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        editor.putString("activity_number", "2");
                        editor.commit();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(Feeling.this, Signup.class);
                        startActivity(intent);
                        finish();
                        return true;
                    }
                });
                popup.show();
            }
        });

        rad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "rad";
                editor.putString("mood", mood);
                editor.commit();
                Intent i = new Intent(Feeling.this, Energy.class);
                startActivity(i);
            }
        });

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "good";
                editor.putString("mood", mood);
                editor.commit();
                Intent i = new Intent(Feeling.this, Energy.class);
                startActivity(i);
            }
        });

        meh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "meh";
                editor.putString("mood", mood);
                editor.commit();
                Intent i = new Intent(Feeling.this, Energy.class);
                startActivity(i);
            }
        });

        bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "bad";
                editor.putString("mood", mood);
                editor.commit();
                Intent i = new Intent(Feeling.this, Energy.class);
                startActivity(i);
            }
        });
    }
}
