package com.example.listviewapp1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Contact> data;

    public ProductAdapter(Context mContext, ArrayList<Contact> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Contact getItem(int i) {
        return this.data.get((i));
    }

    @Override
    public long getItemId(int i) {
        return Integer.parseInt(this.data.get((i)).getTel());
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inf = LayoutInflater.from(mContext);
        View v = inf.inflate(R.layout.product_item,null);

        Contact p = data.get(position);

        if (p != null) {
            TextView tvFirstName = v.findViewById(R.id.tv_firstName_contact);
            TextView tvLastName = v.findViewById(R.id.tv_lastName_contact);
            TextView tvNumber = v.findViewById(R.id.tv_number_contact);
            ImageView iv_call = v.findViewById(R.id.btn_call_contact);
            ImageView iv_edit = v.findViewById(R.id.btn_edit_contact);
            ImageView iv_delete = v.findViewById(R.id.btn_delete_contact);
            if (tvFirstName != null && tvLastName!= null && tvNumber!= null) {
                tvFirstName.setText(p.getName());
                tvLastName.setText(p.getLastName());
                tvNumber.setText(p.getTel());
                iv_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+p.getTel()));
                        mContext.startActivity(callIntent);
                    }
                });
                iv_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        
                    }
                });
                iv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data.remove(position);
                        notifyDataSetChanged();
                    }
                });
            }
        }

        return v;
    }
}
