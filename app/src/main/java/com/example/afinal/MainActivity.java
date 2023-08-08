package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button btnlogin;
    DBHelper DB;
    TextView btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username =  findViewById(R.id.username1);
        password =  findViewById(R.id.password1);
        btnlogin =  findViewById(R.id.btnsignin1);
        btn_signup= findViewById(R.id.btnsignup1);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(view -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(user.equals("")||pass.equals(""))
                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if(checkuserpass){
                    Toast.makeText(MainActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_signup.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

    }
}