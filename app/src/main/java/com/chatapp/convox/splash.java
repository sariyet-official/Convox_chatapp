package com.chatapp.convox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splash extends AppCompatActivity {
    TextView logotxt,tag1,tag2;
    ImageView logo;
    Animation top_anim,bot_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        logo=findViewById(R.id.logo);
        logotxt=findViewById(R.id.logotxt);
        tag1=findViewById(R.id.splashtag1);
        tag2=findViewById(R.id.splashtag2);

        top_anim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bot_anim= AnimationUtils.loadAnimation(this,R.anim.bot_animation);

        logo.setAnimation(top_anim);
        logotxt.setAnimation(top_anim);
        tag1.setAnimation(bot_anim);
        tag2.setAnimation(bot_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splash.this, signup.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}