package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class code_email extends AppCompatActivity {
    public static String code = "1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_email);
        EditText code_input = findViewById(R.id.code_input);
        //String code = "1234";
        Bundle args = getIntent().getExtras();
        //Log.e("Log", "Лог: "+args.getString("mail"));
        //Log.e("Log", "Лог: "+args.getInt("code"));
        //Database.get.execute("code", "mail", args.getString("mail"));
        code = Integer.toString(args.getInt("code"));
        TextView retry = findViewById(R.id.retry);
        retry.setEnabled(true);
        code_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 4){
                    if(s.toString().equals(code)){
                        Intent pass = new Intent(code_email.this, create_password.class);
                        code_email.this.startActivity(pass);
                        code_email.this.finish();
                    }
                }
            }
        });

        new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                retry.setText("Отправить код повторно можно\nчерез "+millisUntilFinished / 1000+" секунд");
            }

            public void onFinish() {
                retry.setClickable(true);
                retry.setText("Отправить код снова");
                retry.setTextColor(Color.parseColor("#FF000000"));
            }
        }.start();
    }

    public void retry_click(View v){
        TextView retry = findViewById(R.id.retry);
        if(retry.isClickable()) {
            Intent pass = new Intent(code_email.this, create_password.class);
            code_email.this.startActivity(pass);
            code_email.this.finish();
        }
    }
}

