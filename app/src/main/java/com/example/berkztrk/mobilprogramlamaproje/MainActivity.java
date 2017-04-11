package com.example.berkztrk.mobilprogramlamaproje;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    List<String> title,author,date,day,id,content,link;
    List<Boolean> boolLink;
    String jsonData;
    ListView lv;
    String url ="http://46.101.173.39/mobilproje/duyuru.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        title = new ArrayList<String>();
        author = new ArrayList<String>();
        date = new ArrayList<String>();
        day=new ArrayList<String>();
        id=new ArrayList<String>();
        content=new ArrayList<String>();
        boolLink=new ArrayList<Boolean>();
        link=new ArrayList<String>();
        lv = (ListView)findViewById(R.id.listview);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent detail = new Intent(MainActivity.this,detailActivity.class);
                detail.putExtra("baslik",MainActivity.this.title.get(position).toString());
                detail.putExtra("icerik",MainActivity.this.content.get(position).toString());
                detail.putExtra("yazar",MainActivity.this.author.get(position).toString());
                if(link.get(position).toString()!="false") {
                    detail.putExtra("link", MainActivity.this.link.get(position).toString());
                }
                startActivity(detail);
            }
        });


        new DownloadWebPageTask().execute(url);



    }
    public class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;
        @Override
        protected String doInBackground(String... urls) {
            String response = "";

            for (String url : urls) {
                HttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);

                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();
                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content,Charset.forName("UTF-8")),8);
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Yükleniyor..");
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            jsonData = result;
            try{

                JSONArray jsonArray = new JSONArray(jsonData);
                title.clear();
                author.clear();
                date.clear();
                day.clear();
                content.clear();
                link.clear();
                for (int i = 0; i < jsonArray.length() ; i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    title.add(i,json.getString("title"));
                    author.add(i,json.getString("author"));
                    date.add(i,json.getString("date"));
                    day.add(i,json.getString("day"));
                    id.add(i,json.getString("id"));
                    content.add(i,json.getString("content"));
                    if(content.get(i).toString().contains("Ek:")){
                        Uri uri=Uri.parse(content.get(i).toString());
                        String convertUri=uri.toString();
                        String sonuc = convertUri.substring(convertUri.indexOf("Ek:") + 4, convertUri.indexOf("*"));
                        sonuc=sonuc.replace(" ","%20");
                        System.out.println("SONUC EKLENDİ");
                        link.add(i,sonuc);
                    }else{
                        link.add(i,"false");
                        System.out.println("false eklendi");
                    }
                }
                lv.setAdapter(null);
                lv.setAdapter(new CustomAdapter(MainActivity.this,title,author,date,day));

            }
            catch (Exception e){

            }
            pDialog.dismiss();
        }
    }
}



