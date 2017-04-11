package com.example.berkztrk.mobilprogramlamaproje;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Onurp on 28.12.2016.
 */

public class detailActivity extends Activity {
    TextView title,content,author,link;
    Button kapat;
    String baslik,icerik,yazar,linkk;
    String url = "http://46.101.173.39/mobilproje/duyuru.php";
    String combineLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title=(TextView)findViewById(R.id.tvDetTitle);
        content=(TextView)findViewById(R.id.tvDetContent);
        author=(TextView)findViewById(R.id.tvDetAuthor);
        kapat=(Button)findViewById(R.id.bttnClose);
        link=(TextView) findViewById(R.id.tvDetLink);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        link.setClickable(true);
        baslik=getIntent().getStringExtra("baslik");
        icerik=getIntent().getStringExtra("icerik");
        yazar=getIntent().getStringExtra("yazar");
        linkk=getIntent().getStringExtra("link");
        System.out.println(linkk);
        String convertLink="<a href=''> İndir </a>";
        String convertLinkFirst=convertLink.substring(0,8);
        String convertLinkLast=convertLink.substring(10,22);
        System.out.println(convertLinkLast);
        combineLink=convertLinkFirst+linkk+convertLinkLast;

        setup();
        kapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(detailActivity.this,webviewActivity.class);
                intent.putExtra("link",linkk);
                startActivity(intent);
            }
        });
    }


    public void setup(){
        title.setText(baslik);
        content.setText(icerik);
        author.setText(yazar);
        if(linkk ==null){
            System.out.println("LİNK VERİLMEMİŞ");
            link.setText("Link verilmemiş.");
        }else{
            link.setText(Html.fromHtml(combineLink));
        }

    }

}

