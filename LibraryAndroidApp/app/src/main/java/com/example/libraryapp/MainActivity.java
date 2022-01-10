package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     TextView jsonValue;
    ArrayList<Book> bookList;
    private ListView listView;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookList = new ArrayList<>();
        jsonValue = findViewById(R.id.jsonValue);
        listView = findViewById(R.id.listView);
        myAdapter=new MyAdapter(this,R.layout.list_row_item,bookList);

        // now setting the simpleAdapter to the ListView
        listView.setAdapter(myAdapter);
        new libaryBook().execute();
    }

    class libaryBook extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String response = HttpRequest.excuteGet("http://ipaddress:8300/books");
            return response;
        }
        @Override
        protected void onPostExecute(String result) {
            try {
             //   {"id":1,"category":"Kids","bookName":"Sample book","author":"VB"}
                JSONArray jsonArray = new JSONArray(result);
              for(int i=0;i<jsonArray.length();i++){
                  JSONObject jsonObject = jsonArray.getJSONObject(i);
                  Book book = new Book();
                  int id = jsonObject.getInt("id");
                  String category = jsonObject.getString("category");
                  String bookName = jsonObject.getString("bookName");
                  String author = jsonObject.getString("author");
                  book.setId(id);
                  book.setBookName(bookName);
                  book.setCategory(category);
                  book.setAuthor(author);
                  bookList.add(book);

              }
                myAdapter.notifyDataSetChanged();
              //jsonValue.setText(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}