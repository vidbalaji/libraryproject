package com.example.libraryapp;

import static android.widget.Toast.LENGTH_LONG;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeoutException;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.DeliverCallback;

public class MainActivity extends AppCompatActivity {
    TextView jsonValue;
    ArrayList<Book> bookList;
    private ListView listView;
    MyAdapter myAdapter;
    Thread subscribeThread;
    private ConnectionFactory factory;
    Connection connection;
    private Channel channel;
    private LibaryBook libaryBook;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        subscribeThread.interrupt();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookList = new ArrayList<>();
        jsonValue = findViewById(R.id.jsonValue);
        listView = findViewById(R.id.listView);
        myAdapter = new MyAdapter(this, R.layout.list_row_item, bookList);

        // now setting the simpleAdapter to the ListView
        listView.setAdapter(myAdapter);
        final Handler incomingMessageHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String message = msg.getData().getString("msg");
                // TextView tv = (TextView) findViewById(R.id.textView);
                Toast.makeText(getApplicationContext(), "New Book", Toast.LENGTH_LONG).show();
                Date now = new Date();
                Log.d("handleMessage", "[r] " + message);
                SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                //  myAdapter.notifyDataSetChanged();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              new LibaryBook().execute();
                //  tv.append(ft.format(now) + ' ' + message + '\n');
            }
        };
        subscribe(incomingMessageHandler);
        libaryBook = new LibaryBook();
        libaryBook.execute();


    }

    void subscribe(final Handler handler) {
        subscribeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                factory = new ConnectionFactory();
                String uri = "amqp://<user>:<pass>@ipaddress:5672";
                     
                factory.setAutomaticRecoveryEnabled(false);

                try {
                    factory.setUri(uri);
                    connection = factory.newConnection();
                    channel = connection.createChannel();

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {


                        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                            String message = new String(delivery.getBody());
                            Log.d("", "[r] " + message);
                            Message msg = handler.obtainMessage();
                            Bundle bundle = new Bundle();

                            bundle.putString("msg", message);
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        };
                        channel.basicConsume("newbookmobile", true, deliverCallback, consumerTag -> {
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        subscribeThread.start();
    }

    class LibaryBook extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String response = HttpRequest.excuteGet("http://Ipaddress:8300/books");
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                //   {"id":1,"category":"Kids","bookName":"Sample book","author":"VB"}
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
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