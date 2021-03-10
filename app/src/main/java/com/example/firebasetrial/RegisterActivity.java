package com.example.firebasetrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        email=findViewById(R.id.email2);
        password=findViewById(R.id.password2);
        register=findViewById(R.id.register2);
        auth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email=email.getText().toString();
                String txt_password=password.getText().toString();
                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){

                    Toast.makeText(RegisterActivity.this,"Empty Credentials",Toast.LENGTH_LONG).show();
                }
                else if(txt_password.length()<6){
                    Toast.makeText(RegisterActivity.this,"Please enter Strong Password",Toast.LENGTH_LONG).show();
                }
                else{
                    registeruser(txt_email,txt_password);
                }

            }
        });
    }

    private void registeruser(String email, String password) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"User Registered Successfully...",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Registration Faild ...",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}