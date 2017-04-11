package com.example.berkztrk.mobilprogramlamaproje;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Onurp on 26.12.2016.
 */

public class spActivity extends AppCompatActivity {
    AutoCompleteTextView et;
    Button btn;
    String isim;
    private static String[] names = new String[] { "burak", "onur",
            "berk", "koray", "enes", "ahmet", "ali",
            "gamze", "melike", "selin", "nevcihan", "ömer", "bilal",
            "elif" };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        et=(AutoCompleteTextView)findViewById(R.id.etAd);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,names);
        et.setThreshold(1);
        et.setAdapter(adapter);

        btn=(Button)findViewById(R.id.bttnDevam);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isim=et.getText().toString();
                if(isim.matches("")){
                    Toast.makeText(spActivity.this,"İsim giriniz.",Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences sharedPreferences=getSharedPreferences("bilgiler",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name",isim);
                    editor.apply();
                    Toast.makeText(spActivity.this, "Kayıt Yapıldı.",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(spActivity.this,menuActivity.class);
                    finish();
                    startActivity(intent);
                }

            }
        });
    }
}
