package com.example.listviewapp1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton addBtn;
    private EditText inuputSearch;
    private MyDBHelper DB;
    private ArrayList<Contact> data ;
    private ArrayList<Contact> filtredContacts ;
    private RecyclerView rv;
    private String searchValue = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();

        inuputSearch = findViewById(R.id.inputSearchShow);
        rv = findViewById(R.id.rv);

        DB = new MyDBHelper(HomeActivity.this);
        getData();

        addBtn = (FloatingActionButton)findViewById(R.id.addBtnHome);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddIntent = new Intent(HomeActivity.this,AddActivity.class);
                HomeActivity.this.startActivity(AddIntent);
            }
        });


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
                HomeActivity.this,
                filtredContacts);
        rv.setAdapter(rvAdapter);
    }

    private void filter(){
        if(searchValue.length() == 0) {
            filtredContacts = data;
            MyRecycleViewAdapter rvAdapter = new MyRecycleViewAdapter(
                    HomeActivity.this,
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
                    HomeActivity.this,
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