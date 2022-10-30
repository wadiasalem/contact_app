package com.example.listviewapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.*;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private EditText inuputSearch;
    private ArrayList<Contact> filtredContacts = new ArrayList<>();
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filtredContacts = HomeActivity.contactList;

        inuputSearch = findViewById(R.id.inputSearchShow);
        rv = findViewById(R.id.rv);
        /*rv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact calledContact = HomeActivity.contactList.get(i);
                Intent intentCall = new Intent(Intent.ACTION_DIAL);
                intentCall.setData(Uri.parse("tel:"+calledContact.getTel()));
                startActivity(intentCall);;
                return false;
            }
        });*/

        inuputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchValue = editable.toString().toUpperCase();

                if(searchValue.length() == 0) {
                    filtredContacts = HomeActivity.contactList;
                    ProductAdapter pAdapter = new ProductAdapter(MainActivity.this, filtredContacts);
                    MyRecycleViewAdapter rvAdapter = new MyRecycleViewAdapter(
                            MainActivity.this,
                            filtredContacts);
                    rv.setAdapter(rvAdapter);
                }else{
                    filtredContacts = new ArrayList<Contact>();
                    HomeActivity.contactList.forEach((contact)->{
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
                    ProductAdapter pAdapter = new ProductAdapter(
                            MainActivity.this,
                            filtredContacts);
                    rv.setAdapter(rvAdapter);
                }
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                filtredContacts);
        MyRecycleViewAdapter rvAdapter = new MyRecycleViewAdapter(
                MainActivity.this,
                filtredContacts);
        ProductAdapter pAdapter = new ProductAdapter(this, filtredContacts);
        rv.setAdapter(rvAdapter);
    }
}