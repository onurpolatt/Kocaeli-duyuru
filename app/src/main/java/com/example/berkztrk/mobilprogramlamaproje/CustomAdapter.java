package com.example.berkztrk.mobilprogramlamaproje;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Onurp on 28.12.2016.
 */

public class CustomAdapter extends BaseAdapter {
    List<String> title,author,date,day;
    Context context;
    private static LayoutInflater inflater = null;
    public  CustomAdapter(Activity activity, List<String> title, List<String> author, List<String> day, List<String> date){
        this.title = title;
        this.author = author;
        this.date = date;
        this.day =day;
        this.context = activity;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        TextView title,author,date,day;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.row_list, null);
        holder.title = (TextView) rowView.findViewById(R.id.tvTitle);
        holder.author = (TextView) rowView.findViewById(R.id.tvAuthor);
        holder.date = (TextView) rowView.findViewById(R.id.tvDate);
        holder.day = (TextView) rowView.findViewById(R.id.tvDay);

        holder.title.setText(this.title.get(position).toString());
        holder.author.setText(this.author.get(position).toString());
        holder.date.setText(this.date.get(position).toString());
        holder.day.setText(this.day.get(position).toString());


        return rowView;
    }
}
