package com.example.listviewapp1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewAdapter> {

    Context context;
    ArrayList<Contact> data;

    public MyRecycleViewAdapter(Context context, ArrayList<Contact> data) {
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
            iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition(); // return position
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("edition");
                    alert.setMessage("Modifier des infos");
                    LayoutInflater inf = LayoutInflater.from(context);
                    View view2 = inf.inflate(R.layout.product_item,null);
                    alert.setView(view2);
                    alert.show();
                }
            });
            iv_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+data.get(index).getTel()));
                    context.startActivity(callIntent);
                }
            });
            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    data.remove(index);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
