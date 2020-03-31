package com.mitadt.theguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Manual extends AppCompatActivity {

    TextView name1,name2,name3,title1,title2,title3,quote1,quote2,quote3;
    ImageView pic1,pic2,pic3;
    TextView manual_bacl;
    LinearLayout next1,next2,next3;
    int pic=0;
    String name,title,quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        name1=findViewById(R.id.name1);
        name2=findViewById(R.id.name2);
        name3=findViewById(R.id.name3);
        manual_bacl=findViewById(R.id.manual_back);
        quote3=findViewById(R.id.quote3);
        quote2=findViewById(R.id.quote2);
        quote1=findViewById(R.id.quote1);
        title3=findViewById(R.id.title3);
        title2=findViewById(R.id.title2);
        title1=findViewById(R.id.title1);
        pic3=findViewById(R.id.pic3);
        pic2=findViewById(R.id.pic2);
        pic1=findViewById(R.id.pic1);
        next3=findViewById(R.id.next3);
        next2=findViewById(R.id.next2);
        next1=findViewById(R.id.next1);

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pic=1;
                name=name1.getText().toString();
                title=title1.getText().toString();
                quote=quote1.getText().toString();
                Intent intent=new Intent(Manual.this,com.mitadt.theguide.Manual_Result.class);
                intent.putExtra("name",name);
                intent.putExtra("title",title);
                intent.putExtra("quote",quote);
                intent.putExtra("pic",pic);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            }
        });

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pic=2;
                name=name2.getText().toString();
                title=title2.getText().toString();
                quote=quote2.getText().toString();
                Intent intent=new Intent(Manual.this,com.mitadt.theguide.Manual_Result.class);
                intent.putExtra("name",name);
                intent.putExtra("title",title);
                intent.putExtra("quote",quote);
                intent.putExtra("pic",pic);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            }
        });

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pic=3;
                name=name3.getText().toString();
                title=title3.getText().toString();
                quote=quote3.getText().toString();
                Intent intent=new Intent(Manual.this,com.mitadt.theguide.Manual_Result.class);
                intent.putExtra("name",name);
                intent.putExtra("title",title);
                intent.putExtra("quote",quote);
                intent.putExtra("pic",pic);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            }
        });

        manual_bacl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Manual.this,com.mitadt.theguide.HomePage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
            }
        });

    }
}
