package com.example.kadai3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<String[]> data;


    public ListAdapter(Context context, List<String[]> data){
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        }

        String[] row = data.get(position);

        TextView column1 = convertView.findViewById(R.id.column1);
        TextView column2 = convertView.findViewById(R.id.column2);
        TextView column3 = convertView.findViewById(R.id.column3);
        TextView column4 = convertView.findViewById(R.id.column4);

        column1.setText(row[0]);
        column2.setText(row[1]);
        column3.setText(row[2]);
        column4.setText(row[3]);

        return convertView;
    }
}
