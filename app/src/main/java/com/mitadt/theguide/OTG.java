package com.mitadt.theguide;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class OTG extends AppCompatActivity {

    int i=0;
    static int str[];
    static String add[];
    int ctr=0;
    String TAG="hello";
    BluetoothAdapter bluetoothAdapter;
    LinearLayout or_back,or_player;
    TextView or_name,or_title,or_quote,or_player_name,or_intro,or_player_title,or_read;
    SeekBar or_player_seek;
    ImageView or_pic,or_player_play,or_player_pause;
    LinearLayout or_dis;

    TextView protext,otg_back;
    ProgressBar pro;

    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;
    Animation to;

    ProgressDialog load;

    int max_rssi;
    String max_add;

    String audio1,audio2,audio3;

    int flag=0;
    String flag_add="";
    int ch=0;
    int hello=0;
    int tri=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otg__result);


        or_back = findViewById(R.id.or_back);
        or_player = findViewById(R.id.or_player);
        or_player_seek = findViewById(R.id.or_player_seek);
        or_intro = findViewById(R.id.or_intro);
        or_player_title = findViewById(R.id.or_player_title);
        or_player_name = findViewById(R.id.or_player_name);
        or_name = findViewById(R.id.or_name);
        or_quote = findViewById(R.id.or_quote);
        or_title = findViewById(R.id.or_title);
        or_read = findViewById(R.id.or_read);
        or_pic = findViewById(R.id.or_pic);
        pro=findViewById(R.id.proor);
        or_dis=findViewById(R.id.or_dis);
        protext=findViewById(R.id.protextor);
        otg_back=findViewById(R.id.otg_back);
        or_player_play = findViewById(R.id.or_player_play);
        or_player_pause = findViewById(R.id.or_player_pause);
        handler = new Handler();

        or_player_play.setVisibility(View.GONE);
        or_player.setVisibility(View.GONE);
        or_back.setVisibility(View.GONE);
        or_dis.setVisibility(View.GONE);
        or_read.setVisibility(View.GONE);

        otg_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OTG.this,com.mitadt.theguide.HomePage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
            }
        });

        audio1 = "https://firebasestorage.googleapis.com/v0/b/theguide-448e0.appspot.com/o/gandhiji_audio%20(mp3cut.net).mp3?alt=media&token=d6001a81-733e-489b-92af-fbaf2d986f7a";
        audio2 = "https://firebasestorage.googleapis.com/v0/b/theguide-448e0.appspot.com/o/swamiji_audio.mp3?alt=media&token=dbea888a-5e77-430f-b970-376a2ba6d8fa";
        audio3 = "https://firebasestorage.googleapis.com/v0/b/theguide-448e0.appspot.com/o/bose_audio.mp3?alt=media&token=d2ec5f7a-de32-41c1-9ebc-a98f9710aa05";


        mediaPlayer = new MediaPlayer();

        or_back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(OTG.this, com.mitadt.theguide.HomePage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                mediaPlayer.seekTo(0);
            }
        });

        to= AnimationUtils.loadAnimation(this,R.anim.push_up_out);



        str=new int[3];
        add=new String[3];

        add[0]="AC:ED:5C:D7:DC:BD"; //changed
        add[1]="A4:CF:12:15:F0:E2";
        add[2]="30:AE:A4:BD:C3:DA";

        str[0]=-200;
        str[1]=-200;
        str[2]=-200;

        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.startDiscovery();
        }
        else
        {
            Intent intent=new Intent(bluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,101);
        }

        IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver,filter);



    }

    private final BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action))
            {

                BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);

                if(device.getAddress().equals("AC:ED:5C:D7:DC:BD"))//guide a
                {
                    str[0]=rssi;
                }
                if(device.getAddress().equals("A4:CF:12:15:F0:E2")) //ayush
                {
                    str[1]=rssi;
                }
                if(device.getAddress().equals("30:AE:A4:BD:C3:DA")) //pc
                {
                    str[2]=rssi;
                }

                max_rssi=-200;
                max_add=add[0];

                for(i=0;i<3;i++)
                {
                    if(str[i]>max_rssi)
                    {
                        max_rssi=str[i];
                        max_add=add[i];

                    }
                }


                Log.i(TAG,"Max RSSI : "+max_rssi+" Add. : "+max_add);



                if (max_add.equals("AC:ED:5C:D7:DC:BD") && max_rssi!=-200 && !flag_add.equals("AC:ED:5C:D7:DC:BD") && ch==0) {

                    bluetoothAdapter.cancelDiscovery();
                    flag_add="AC:ED:5C:D7:DC:BD";
                    mediaPlayer.reset();


                    if(or_back.getVisibility()==View.GONE) {
                        or_back.setVisibility(View.VISIBLE);
                        or_dis.setVisibility(View.VISIBLE);
                        or_player.setVisibility(View.VISIBLE);
                        or_read.setVisibility(View.VISIBLE);
                    }

                    if(pro.getVisibility()==View.VISIBLE) {
                        pro.setVisibility(View.GONE);
                        protext.setVisibility(View.GONE);
                        otg_back.setVisibility(View.GONE);
                    }

                    or_name.setText(R.string.first_name);
                    or_title.setText(R.string.first_title);
                    or_quote.setText(R.string.first_quote);
                    or_intro.setText(R.string.gandhiji);
                    or_pic.setImageResource(R.drawable.gandhiji);
                    or_player_name.setText(R.string.first_name);
                    or_player_title.setText(R.string.first_title);

                    or_read.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Mahatma_Gandhi"));
                            startActivity(intent);
                            overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
                        }
                    });

                    new AsyncTask<Void,Void,Void>()
                    {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            ch=1;
                            load= ProgressDialog.show(OTG.this,"","Loading...",true);

                        }

                        @Override
                        protected Void doInBackground(Void... voids) {

                            try {
                                mediaPlayer.setDataSource(audio1);
                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        or_player_seek.setMax(mediaPlayer.getDuration());
                                        mp.start();
                                        playCycle();
                                    }
                                });
                                mediaPlayer.prepare();
                            }catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            load.dismiss();
                            ch=0;
                            if(bluetoothAdapter.isDiscovering())
                            {
                                bluetoothAdapter.cancelDiscovery();
                                //  unregisterReceiver(broadcastReceiver);
                            }

                            bluetoothAdapter.startDiscovery();

                        }
                    }.execute();


                    or_player_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                            if(input)
                            {
                                mediaPlayer.seekTo(progress);
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(final MediaPlayer mp) {
                            mp.seekTo(0);
                            mp.start();
                        }
                    });

                    or_player_play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mediaPlayer.start();
                            playCycle();
                            or_player_play.setVisibility(View.GONE);
                            or_player_pause.setVisibility(View.VISIBLE);

                            if (bluetoothAdapter.isDiscovering()) {
                                bluetoothAdapter.cancelDiscovery();
                                //  unregisterReceiver(broadcastReceiver);
                            }
                            bluetoothAdapter.startDiscovery();
                        }
                    });


                    or_player_pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mediaPlayer.pause();
                            playCycle();
                            or_player_pause.setVisibility(View.GONE);
                            or_player_play.setVisibility(View.VISIBLE);

                            if (bluetoothAdapter.isDiscovering()) {
                                bluetoothAdapter.cancelDiscovery();
                                //  unregisterReceiver(broadcastReceiver);
                            }
                            bluetoothAdapter.startDiscovery();
                        }
                    });


                }


                if (max_add.equals("A4:CF:12:15:F0:E2") && max_rssi!=-200 && !flag_add.equals("A4:CF:12:15:F0:E2") && ch==0) {

                    bluetoothAdapter.cancelDiscovery();
                    flag_add="A4:CF:12:15:F0:E2";
                    mediaPlayer.reset();



                   if(or_back.getVisibility()==View.GONE) {
                        or_back.setVisibility(View.VISIBLE);
                        or_dis.setVisibility(View.VISIBLE);
                        or_player.setVisibility(View.VISIBLE);
                        or_read.setVisibility(View.VISIBLE);
                    }

                    if(pro.getVisibility()==View.VISIBLE) {
                        pro.setVisibility(View.GONE);
                        protext.setVisibility(View.GONE);
                        otg_back.setVisibility(View.GONE);
                    }


                    or_name.setText(R.string.second_name);
                    or_title.setText(R.string.second_title);
                    or_quote.setText(R.string.second_quote);
                    or_intro.setText(R.string.swamiji);
                    or_pic.setImageResource(R.drawable.swamiji);
                    or_player_name.setText(R.string.second_name);
                    or_player_title.setText(R.string.second_title);

                    or_read.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Swami_Vivekananda"));
                            startActivity(intent);
                            overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
                        }
                    });

                    new AsyncTask<Void,Void,Void>()
                    {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            ch=1;
                            load= ProgressDialog.show(OTG.this,"","Loading...",true);


                        }

                        @Override
                        protected Void doInBackground(Void... voids) {

                            try {
                                mediaPlayer.setDataSource(audio2);
                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        or_player_seek.setMax(mediaPlayer.getDuration());
                                        mp.start();
                                        playCycle();
                                    }
                                });
                                mediaPlayer.prepare();
                            }catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            load.dismiss();
                            ch=0;
                            if(bluetoothAdapter.isDiscovering())
                            {
                                bluetoothAdapter.cancelDiscovery();
                                //  unregisterReceiver(broadcastReceiver);
                            }

                            bluetoothAdapter.startDiscovery();

                        }
                    }.execute();


                    or_player_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                            if(input)
                            {
                                mediaPlayer.seekTo(progress);
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(final MediaPlayer mp) {
                            mp.seekTo(0);
                            mp.start();
                        }
                    });

                    or_player_play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mediaPlayer.start();
                            playCycle();
                            or_player_play.setVisibility(View.GONE);
                            or_player_pause.setVisibility(View.VISIBLE);


                            if (bluetoothAdapter.isDiscovering()) {
                                bluetoothAdapter.cancelDiscovery();
                                //  unregisterReceiver(broadcastReceiver);
                            }
                            bluetoothAdapter.startDiscovery();
                        }
                    });


                    or_player_pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mediaPlayer.pause();
                            playCycle();
                            or_player_pause.setVisibility(View.GONE);
                            or_player_play.setVisibility(View.VISIBLE);


                            if (bluetoothAdapter.isDiscovering()) {
                                bluetoothAdapter.cancelDiscovery();
                                //  unregisterReceiver(broadcastReceiver);
                            }
                            bluetoothAdapter.startDiscovery();
                        }
                    });


                }


                if (max_add.equals("30:AE:A4:BD:C3:DA") && max_rssi!=-200 && !flag_add.equals("30:AE:A4:BD:C3:DA") && ch==0) {


                    bluetoothAdapter.cancelDiscovery();
                    flag_add="30:AE:A4:BD:C3:DA";
                    mediaPlayer.reset();


                    if(or_back.getVisibility()==View.GONE) {
                        or_back.setVisibility(View.VISIBLE);
                        or_dis.setVisibility(View.VISIBLE);
                        or_player.setVisibility(View.VISIBLE);
                        or_read.setVisibility(View.VISIBLE);
                    }

                    if(pro.getVisibility()==View.VISIBLE) {
                        pro.setVisibility(View.GONE);
                        protext.setVisibility(View.GONE);
                        otg_back.setVisibility(View.GONE);
                    }


                    or_name.setText(R.string.third_name);
                    or_title.setText(R.string.third_title);
                    or_quote.setText(R.string.third_quote);
                    or_intro.setText(R.string.bose);
                    or_pic.setImageResource(R.drawable.bose);
                    or_player_name.setText(R.string.third_name);
                    or_player_title.setText(R.string.third_title);

                    or_read.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Subhas_Chandra_Bose"));
                            startActivity(intent);
                            overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
                        }
                    });

                    new AsyncTask<Void,Void,Void>()
                    {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            ch=1;
                            load= ProgressDialog.show(OTG.this,"","Loading...",true);


                        }

                        @Override
                        protected Void doInBackground(Void... voids) {

                            try {
                                mediaPlayer.setDataSource(audio3);
                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        or_player_seek.setMax(mediaPlayer.getDuration());
                                        mp.start();
                                        playCycle();
                                    }
                                });
                                mediaPlayer.prepare();
                            }catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            load.dismiss();
                            ch=0;
                            if(bluetoothAdapter.isDiscovering())
                            {
                                bluetoothAdapter.cancelDiscovery();
                                //  unregisterReceiver(broadcastReceiver);
                            }

                            bluetoothAdapter.startDiscovery();
                        }
                    }.execute();


                    or_player_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                            if(input)
                            {
                                mediaPlayer.seekTo(progress);
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    or_player_play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mediaPlayer.start();
                            playCycle();
                            or_player_play.setVisibility(View.GONE);
                            or_player_pause.setVisibility(View.VISIBLE);

                            if (bluetoothAdapter.isDiscovering()) {
                                bluetoothAdapter.cancelDiscovery();
                                //  unregisterReceiver(broadcastReceiver);
                            }
                            bluetoothAdapter.startDiscovery();
                        }
                    });


                    or_player_pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mediaPlayer.pause();
                            playCycle();
                            or_player_pause.setVisibility(View.GONE);
                            or_player_play.setVisibility(View.VISIBLE);

                            if (bluetoothAdapter.isDiscovering()) {
                                bluetoothAdapter.cancelDiscovery();
                                //  unregisterReceiver(broadcastReceiver);
                            }
                            bluetoothAdapter.startDiscovery();
                        }
                    });

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(final MediaPlayer mp) {
                            mp.seekTo(0);
                            mp.start();
                        }
                    });


                }

                    if (hello == 0) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Log.d("trial", "handler worked");

                                if (bluetoothAdapter.isDiscovering()) {
                                    bluetoothAdapter.cancelDiscovery();
                                    //  unregisterReceiver(broadcastReceiver);
                                }
                                bluetoothAdapter.startDiscovery();
                                hello = 1;
                            }
                        }, 4000);
                    }

                hello=0;
              /*  if(bluetoothAdapter.isDiscovering())
                {
                    bluetoothAdapter.cancelDiscovery();
                    //  unregisterReceiver(broadcastReceiver);
                }

                bluetoothAdapter.startDiscovery();
              /*  IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(broadcastReceiver,filter); */


            }

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==RESULT_OK)
        {
            bluetoothAdapter.startDiscovery();
            IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(broadcastReceiver,filter);
        }
    }


    public void playCycle()
    {
        or_player_seek.setProgress(mediaPlayer.getCurrentPosition());

        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
            //  unregisterReceiver(broadcastReceiver);
        }
        bluetoothAdapter.startDiscovery();


        if(mediaPlayer.isPlaying())
        {
            runnable=new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
        playCycle();
        if(or_player_play.getVisibility()==View.VISIBLE)
        {
            or_player_play.setVisibility(View.GONE);
            or_player_pause.setVisibility(View.VISIBLE);
        }
        if(bluetoothAdapter.isDiscovering())
        {
            bluetoothAdapter.cancelDiscovery();
        }

        bluetoothAdapter.startDiscovery();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(OTG.this,com.mitadt.theguide.HomePage.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
}