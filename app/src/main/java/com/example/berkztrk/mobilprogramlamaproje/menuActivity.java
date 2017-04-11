package com.example.berkztrk.mobilprogramlamaproje;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class menuActivity extends AppCompatActivity {
    Button buttonYemek;
    Button buttonDuyuru;
    Button buttonAbout;
    TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        text=(TextView)findViewById(R.id.tvIsim);
        buttonAbout = (Button)findViewById(R.id.btnAbout);
        buttonYemek = (Button)findViewById(R.id.btnYemek);
        buttonDuyuru = (Button)findViewById(R.id.btnDuyuru);
        buttonDuyuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (menuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        buttonYemek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (menuActivity.this,YemekActivity.class);
                startActivity(intent);
            }
        });
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (menuActivity.this,aboutActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences=getSharedPreferences("bilgiler",Context.MODE_PRIVATE);
        String gelenisim = sharedPreferences.getString("name","");
        text.setText("Hoşgeldin "+gelenisim+"!");
    }
    @Override
    public void onBackPressed()
    {

        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
