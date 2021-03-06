package com.example.groupin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProject extends AppCompatActivity {

    EditText p_name,p_start, p_due;
    Button add_project_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        p_name = findViewById(R.id.enter_name);
        p_start = findViewById(R.id.enter_pstart);
        p_due = findViewById(R.id.enter_pdue);
        add_project_btn = findViewById(R.id.add_p);


        add_project_btn.setOnClickListener(view -> {
            DBHelper myDB = new DBHelper(AddProject.this);
            String res=myDB.addProject(p_name.getText().toString().trim(),p_start.getText().toString().trim(),p_due.getText().toString().trim());
            Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddProject.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}