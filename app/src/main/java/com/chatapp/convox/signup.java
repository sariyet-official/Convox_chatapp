package com.chatapp.convox;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class signup extends AppCompatActivity {
    TextView loginbtn;
    Button signupbtn;
    EditText sigusername, sigemail, sigpass;
    CircleImageView sigprofileimg; // ImageView to display profile image
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        loginbtn = findViewById(R.id.logintxt2);
        signupbtn = findViewById(R.id.signupbtn);
        sigusername = findViewById(R.id.useredttxt);
        sigpass = findViewById(R.id.passedttxt);
        sigemail = findViewById(R.id.emailedttxt);
        sigprofileimg = findViewById(R.id.profile_img); // Initialize ImageView

        // Set default image
        sigprofileimg.setImageResource(R.drawable.default_profile_image); // Ensure you have a default image in your drawable folder

        // login-redirect
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
                finish();
            }
        });

        // getting-data
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usenm = sigusername.getText().toString();
                String mail = sigemail.getText().toString();
                String pass = sigpass.getText().toString();
                String status = "Hey, Building connections with Convox !";

                if (TextUtils.isEmpty(usenm) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(signup.this, "Should fill all the details!", Toast.LENGTH_SHORT).show();
                } else if (!mail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    sigemail.setError("Enter a valid email");
                } else if (pass.length() < 6) {
                    sigpass.setError("Password should be six or more characters");
                } else {
                    auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String id = task.getResult().getUser().getUid();
                                DatabaseReference refer = database.getReference("user").child(id);

                                // Set default image URL
                                String defaultImgUrl = "https://img.freepik.com/premium-vector/contact-icon-profile-avatar-icon_1199668-1320.jpg";

                                // Create user object with default image
                                Users users = new Users(defaultImgUrl, usenm, id, mail, pass, status);
                                refer.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(signup.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(signup.this, "Error while creating user..", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
