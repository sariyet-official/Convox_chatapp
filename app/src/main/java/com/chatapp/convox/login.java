package com.chatapp.convox;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class login extends AppCompatActivity {
    TextView signupbtn;
    EditText email,pass;
    Button loginbtn;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        auth= FirebaseAuth.getInstance();
        loginbtn=findViewById(R.id.loginbtn);
        email=findViewById(R.id.loginemail);
        pass=findViewById(R.id.loginpass);
        signupbtn=findViewById(R.id.signuptxt2);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String Pass=pass.getText().toString();

                if((TextUtils.isEmpty(Email))){
                    Toast.makeText(login.this,"Enter the email",Toast.LENGTH_SHORT);
                }else if (TextUtils.isEmpty(Pass)){
                    Toast.makeText(login.this,"Enter the password",Toast.LENGTH_SHORT);
                }else if(!Email.matches(emailPattern)){
                    email.setError("Give a correct email");
                } else if (pass.length()<6) {
                    pass.setError("More than Six Characters");
                    Toast.makeText(login.this,"Needs Six or More Characters",Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                try {
                                    Intent intent=new Intent(login.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }catch (Exception e){
                                    Toast.makeText(login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(login.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }
}