package com.example.classcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class reminder extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText Houredit,Minutedite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        this.setTitle("Reminder");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Houredit = findViewById(R.id.hourselect);
        Minutedite = findViewById(R.id.minuteelect);

        button = findViewById(R.id.setreminder);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Hour = Integer.parseInt(Houredit.getText().toString());
                int Minute = Integer.parseInt(Minutedite.getText().toString());

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR,Hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES,Minute);
                if (Hour<=24 && Minute<=60)
                startActivity(intent);

            }
        });
    }


}