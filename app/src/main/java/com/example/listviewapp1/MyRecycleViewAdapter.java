package com.example.listviewapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewAdapter> {

    MainActivity context;
    ArrayList<Contact> data;

    public MyRecycleViewAdapter(MainActivity context, ArrayList<Contact> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyRecycleViewAdapter.MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(context);
        View v = inf.inflate(R.layout.product_item,null);
        MyViewAdapter va = new MyViewAdapter(v);
        return va;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycleViewAdapter.MyViewAdapter holder, int position) {
        Contact p = data.get(position);
        if (holder.tvFirstName != null && holder.tvLastName!= null && holder.tvNumber!= null) {
            holder.tvFirstName.setText(p.getName());
            holder.tvLastName.setText(p.getLastName());
            holder.tvNumber.setText(p.getTel());
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {
        public TextView tvFirstName,tvLastName,tvNumber;
        public ImageView iv_call,iv_edit,iv_delete;
        public MyViewAdapter(@NonNull View v) {
            super(v);
            tvFirstName = v.findViewById(R.id.tv_firstName_contact);
            tvLastName = v.findViewById(R.id.tv_lastName_contact);
            tvNumber = v.findViewById(R.id.tv_number_contact);
            iv_call = v.findViewById(R.id.btn_call_contact);
            iv_edit = v.findViewById(R.id.btn_edit_contact);
            iv_delete = v.findViewById(R.id.btn_delete_contact);

            MyDBHelper DB = new MyDBHelper(context);

            iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition(); // return position
                    Contact contact = data.get(index);

                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    LayoutInflater inf = LayoutInflater.from(context);
                    View dialogView = inf.inflate(R.layout.edit_contact_dialog,null);
                    alert.setView(dialogView);

                    TextView tvDialName,tvDialLastName,tvDialNumber ;
                    tvDialName= dialogView.findViewById(R.id.tv_edit_firstname_contact_dialog);
                    tvDialLastName= dialogView.findViewById(R.id.tv_edit_lastname_contact_dialog);
                    tvDialNumber= dialogView.findViewById(R.id.tv_edit_tel_contact_dialog);
                    tvDialName.setText(contact.getName());
                    tvDialLastName.setText(contact.getLastName());
                    tvDialNumber.setText(contact.getTel());

                    alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Contact newContact = new Contact(
                                    tvDialName.getText().toString(),
                                    tvDialLastName.getText().toString(),
                                    tvDialNumber.getText().toString());
                            DB.updateContact(contact.getTel(),newContact);
                            context.getData();

                        }
                    });

                    alert.create();
                    alert.show();
                }
            });

            iv_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+data.get(index).getTel()));
                    context.startActivity(callIntent);
                }
            });

            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition(); // return position
                    Contact contact = data.get(index);
                    DB.deleteContact(contact.getTel());
                    context.getData();
                }
            });
        }
    }
}
