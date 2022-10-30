package com.example.listviewapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    private Button btnAdd,btnCancel,btnInit;
    private EditText inputName,inputLastName,inputTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAdd = findViewById(R.id.addBtnAdd);
        btnCancel = findViewById(R.id.CancelBtnAdd);
        btnInit = findViewById(R.id.initBtnAdd);

        inputName = findViewById(R.id.inputFirstNameAdd);
        inputLastName = findViewById(R.id.inputLastNameAdd);
        inputTel = findViewById(R.id.inputTelAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                String lastName = inputLastName.getText().toString();
                String tel = inputTel.getText().toString();

                HomeActivity.contactList.add(new Contact(name,lastName,tel));
                reset();

            }
        });

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddActivity.this.finish();
            }
        });

    }

    private void reset() {
        inputName.setText("");
        inputLastName.setText("");
        inputTel.setText("");
    }
}