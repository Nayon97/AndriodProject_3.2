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

public class todo extends AppCompatActivity {
    Button button;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

       this.setTitle("Todo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DB = new DbHelper(this);
        button = findViewById(R.id.showtodo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.ShowTodoData();
                if (res.getCount()==0){
                    Toast.makeText(todo.this, "No data Found", Toast.LENGTH_SHORT).show();

                }else{
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append(res.getString(1)+"\n");
                    buffer.append("Subject: "+res.getString(2)+"\n");
                    buffer.append("Last Date of Submission: "+res.getString(3)+"\n");
                    buffer.append("Last TIme Of Submission"+res.getString(4)+"\n");
                    buffer.append("Notes (If needed): "+res.getString(5)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(todo.this);
                builder.setCancelable(true);
                builder.setTitle("Todos List");
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
            startActivity(new Intent(this, ToDoAdding.class));
        }else if (item.getItemId()==R.id.delete){
            startActivity(new Intent(this, Tododeleting.class));
        }
        return super.onOptionsItemSelected(item);
    }


}