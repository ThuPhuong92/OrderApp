package com.example.orderapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

     EditText username, password, rePassword;
     Button btnSignUp;
     TextView tvLogin;

     DBHelper db;

     @SuppressLint("MissingInflatedId")
     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_sign_up);

          username = findViewById(R.id.edtSignupUsername);
          password = findViewById(R.id.edtSignupPassword);
          rePassword = findViewById(R.id.edtSignupRePassword);
          btnSignUp = findViewById(R.id.btnSignUp);
          tvLogin = findViewById(R.id.tvLogin);
          db = new DBHelper(this);

          btnSignUp.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    String repass = rePassword.getText().toString();

                    if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) ||TextUtils.isEmpty(repass)){
                         Toast.makeText(SignUpActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
                    }else{
                         if(pass.equals(repass)){
                              Boolean checkuser = db.checkUsername(user);
                              if(checkuser == false){
                                   Boolean insert = db.insertData(user, pass);
                                   if(insert == true){
                                        Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                        startActivity(intent);
                                   }else{
                                        Toast.makeText(SignUpActivity.this, "Registered Failed", Toast.LENGTH_SHORT).show();
                                   }
                              }else{
                                   Toast.makeText(SignUpActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                              }
                         }else{
                              Toast.makeText(SignUpActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                         }
                    }
               }
          });

          tvLogin.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
               }
          });
     }
}
