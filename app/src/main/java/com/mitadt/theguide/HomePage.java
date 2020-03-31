package com.mitadt.theguide;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    TextView manual,otg;
    private static final int ENABLE_BT_REQUEST=101;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        otg=findViewById(R.id.otg);
        manual=findViewById(R.id.manual);

        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,com.mitadt.theguide.Manual.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            }
        });

        otg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bluetoothAdapter==null)
                {
                    Toast.makeText(HomePage.this,"Device does not support bluetooth.",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent,ENABLE_BT_REQUEST);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ENABLE_BT_REQUEST && resultCode==RESULT_OK)
        {
           Intent intent=new Intent(HomePage.this,com.mitadt.theguide.OTG.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        }
        else
        {
            Toast.makeText(HomePage.this,"Please enable bluetooth",Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
