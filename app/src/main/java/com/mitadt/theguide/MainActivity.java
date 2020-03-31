package com.mitadt.theguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView main_logo;
    TextView main_into,main_protext;
    ProgressBar main_pro;
    Animation fi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_logo=findViewById(R.id.main_logo);
        main_into=findViewById(R.id.main_intro);
        main_protext=findViewById(R.id.main_protext);
        main_pro=findViewById(R.id.main_pro);
        fi= AnimationUtils.loadAnimation(this,R.anim.appear1);

        main_logo.clearAnimation();
        main_into.clearAnimation();
        main_pro.clearAnimation();
        main_protext.clearAnimation();

        main_logo.startAnimation(fi);
        main_into.startAnimation(fi);
        main_pro.startAnimation(fi);
        main_protext.startAnimation(fi);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(MainActivity.this,com.mitadt.theguide.HomePage.class);
                startActivity(intent);
                MainActivity.this.finish();
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);

            }
        },2000);
        
    }
}
