package com.example.fruitworld.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fruitworld.Models.Fruit;
import com.example.fruitworld.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Fruit> fruits;
    int layout;

    public MyAdapter(Context context, ArrayList<Fruit> fruits, int layout){
        this.context = context;
        this.fruits = fruits;
        this.layout = layout;
    }

    static class ViewHolder{
        private TextView textName;
        private TextView textOrigin;
        private ImageView icon;

        public ViewHolder(View view){
            textName = (TextView) view.findViewById(R.id.idTextName);
            textOrigin = (TextView) view.findViewById(R.id.idTextOrigin);
            icon = (ImageView) view.findViewById(R.id.idImage);
        }
    }

    @Override
    public int getCount() {
        return fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return fruits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        ViewHolder holder;

        if(v==null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            v = inflater.inflate(layout, null);
            holder = new ViewHolder(v);
            v.setTag(holder);
        }else
            holder = (ViewHolder) v.getTag();

        holder.textName.setText(fruits.get(position).getName());
        holder.textOrigin.setText(fruits.get(position).getOrigin());
        holder.icon.setImageResource(fruits.get(position).getIcon());

        return v;
    }
}
