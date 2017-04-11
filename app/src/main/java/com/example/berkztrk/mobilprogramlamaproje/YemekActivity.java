package com.example.berkztrk.mobilprogramlamaproje;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by Koray on 28.12.2016.
 */

public class YemekActivity extends AppCompatActivity {
    String tarih,corba,anayemek,yardimci,tatli;
    TextView crba,anaymk,yrdmci,ttli,trih;
    String jsonData;
    String url = "http://46.101.173.39/mobilproje/yemek.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_yemek);

        crba=(TextView)findViewById(R.id.tvCorba);
        anaymk=(TextView)findViewById(R.id.tvAnayemek);
        yrdmci=(TextView)findViewById(R.id.tvYardimci);
        ttli=(TextView)findViewById(R.id.tvTatli);
        trih=(TextView)findViewById(R.id.tvTarih);
        new YemekActivity.DownloadWebPageTask().execute(url);
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
            pDialog = new ProgressDialog(YemekActivity.this);
            pDialog.setMessage("Yükleniyor..");
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            jsonData = result;
            try{


                    JSONObject json = new JSONObject(jsonData);

                    corba=json.getString("corba");
                    anayemek=json.getString("anayemek");
                    yardimci=json.getString("yardimciyemek");
                    tatli=json.getString("tatli");
                    tarih=json.getString("tarih");

                    crba.setText(corba);
                    anaymk.setText(anayemek);
                    yrdmci.setText(yardimci);
                    ttli.setText(tatli);
                    trih.setText(tarih);

                pDialog.dismiss();
            }
            catch (Exception e){

            }

        }
    }
}
