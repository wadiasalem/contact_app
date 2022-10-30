package com.example.listviewapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button addBtn,showBtn;
    private TextView tvName;
    public static ArrayList<Contact> contactList = new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        contactList.add(new Contact("Malek","Ben Salah","50249212"));
        contactList.add(new Contact("Adam","Miled","94375635"));
        contactList.add(new Contact("Wadia","Salem","53913143"));

        addBtn = findViewById(R.id.addBtnHome);
        showBtn = findViewById(R.id.editBtnHome);
        tvName = findViewById(R.id.tvName);

        Intent I = this.getIntent();
        Bundle extras = I.getExtras();
        String name = extras.getString("Name");

        tvName.setText("Bienvenue "+name);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddIntent = new Intent(HomeActivity.this,AddActivity.class);
                HomeActivity.this.startActivity(AddIntent);
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ShowIntent = new Intent(HomeActivity.this,MainActivity.class);
                HomeActivity.this.startActivity(ShowIntent);
            }
        });
    }
}