package com.example.classcaller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class routine extends AppCompatActivity {
    Button button;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setTitle("Routine");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DB = new DbHelper(this);
        button = findViewById(R.id.showroutine);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.ShowRoutineData();
                if (res.getCount()==0){
                    Toast.makeText(routine.this, "No data Found", Toast.LENGTH_SHORT).show();

                }else{
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Course Name: "+res.getString(1)+"\n");
                        buffer.append("Course Code: "+res.getString(2)+"\n");
                        buffer.append("Room Number: "+res.getString(3)+"\n");
                        buffer.append("Course Teacher: "+res.getString(4)+"\n");
                        buffer.append("Day: "+res.getString(5)+"\n");
                        buffer.append("Class Start Time: "+res.getString(6)+"\n");
                        buffer.append("Class End Time: "+res.getString(7)+"\n\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(routine.this);
                    builder.setCancelable(true);
                    builder.setTitle("Routine");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.routineadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add)
        {
            startActivity(new Intent(this, addClass.class));
        }else if (item.getItemId()==R.id.delete){
            startActivity(new Intent(this, deleteclass.class));
        }
        return super.onOptionsItemSelected(item);
    }
}

