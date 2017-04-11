package com.example.berkztrk.mobilprogramlamaproje;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Onurp on 22.12.2016.
 */

public class splashActivity extends AppCompatActivity {
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    SharedPreferences sharedPreferences=getSharedPreferences("bilgiler", Context.MODE_PRIVATE);
                    String name=sharedPreferences.getString("name","");
                    Intent intent;
                    //Toast.makeText(getApplicationContext(),name.toString(), Toast.LENGTH_SHORT);
                    if(name==" " || name=="" ||name==null)
                     intent = new Intent(splashActivity.this,spActivity.class);
                    else
                         intent = new Intent(splashActivity.this,menuActivity.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
