package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btn = findViewById(R.id.login_btn);
        btn.setEnabled(false);
        EditText mail_s = findViewById(R.id.email_input);
        mail_s.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().contains("@")){
                    btn.setEnabled(true);
                }else{
                    btn.setEnabled(false);
                }
            }
        });
    }

    public void login_click(View v){
        EditText str = findViewById(R.id.email_input);
        Random rand = new Random();
        int code = rand.nextInt(8000) + 1000;
        Log.e("Log","Лог: " + Integer.toString(code));
        new mail().execute(str.getText().toString(), Integer.toString(code));
        Intent loginB = new Intent(login.this, code_email.class);
        loginB.putExtra("mail", str.getText().toString());
        loginB.putExtra("code", code);
        startActivity(loginB);

        //login.this.finish();
    }
}
