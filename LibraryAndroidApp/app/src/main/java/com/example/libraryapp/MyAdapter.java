package com.example.libraryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Book> {

    ArrayList<Book> bookList ;

    public MyAdapter(Context context, int textViewResourceId, ArrayList<Book> objects) {
        super(context, textViewResourceId, objects);
        bookList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.list_row_item, null);
        TextView textBook = (TextView) v.findViewById(R.id.textBook);
        TextView textAuthor = (TextView) v.findViewById(R.id.textAuthor);
        TextView textCategory = (TextView) v.findViewById(R.id.textCategory);
        textBook.setText(bookList.get(position).getBookName());
        textAuthor.setText(bookList.get(position).getAuthor());
        textCategory.setText(bookList.get(position).getCategory());
        return v;

    }

}