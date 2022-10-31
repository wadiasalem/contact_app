package com.example.listviewapp1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.*;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private EditText inuputSearch;
    private MyDBHelper DB;
    private ArrayList<Contact> data ;
    private ArrayList<Contact> filtredContacts ;
    private RecyclerView rv;
    private String searchValue = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable color = new ColorDrawable(Color.parseColor("#FFFFFF"));
        actionBar.setBackgroundDrawable(color);
        actionBar.setElevation(0);
        inuputSearch = findViewById(R.id.inputSearchShow);
        rv = findViewById(R.id.rv);

        DB = new MyDBHelper(MainActivity.this);
        getData();


        inuputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchValue = editable.toString().toUpperCase();
                filter();
            }
        });

        MyRecycleViewAdapter rvAdapter = new MyRecycleViewAdapter(
                MainActivity.this,
                filtredContacts);
        rv.setAdapter(rvAdapter);
    }

    private void filter(){
        if(searchValue.length() == 0) {
            filtredContacts = data;
            MyRecycleViewAdapter rvAdapter = new MyRecycleViewAdapter(
                    MainActivity.this,
                    filtredContacts);
            rv.setAdapter(rvAdapter);
        }else{
            filtredContacts = new ArrayList<Contact>();
            data.forEach((contact)->{
                if(contact.name.toUpperCase().contains(searchValue)){
                    filtredContacts.add(contact);
                }else if(contact.lastName.toUpperCase().contains(searchValue)){
                    filtredContacts.add(contact);
                }else if(contact.tel.toUpperCase().contains(searchValue)){
                    filtredContacts.add(contact);
                }
            });

            MyRecycleViewAdapter rvAdapter = new MyRecycleViewAdapter(
                    MainActivity.this,
                    filtredContacts);
            rv.setAdapter(rvAdapter);
        }
    }

    public void getData(){
        data = new ArrayList<>();
        Cursor cursor = DB.getContacts();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Votre contact est vide", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                Contact contact = new Contact(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(0)
                );
                data.add(contact);
            }
        }
        filter();
    }
}