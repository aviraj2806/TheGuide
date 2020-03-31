package com.mitadt.theguide;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class OTG_Result extends AppCompatActivity {

    LinearLayout or_back,or_player;
    TextView or_name,or_title,or_quote,or_player_name,or_intro,or_player_title,or_read;
    SeekBar or_player_seek;
    ImageView or_pic,or_player_play,or_player_pause;


    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;


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
        or_player_play = findViewById(R.id.or_player_play);
        or_player_pause = findViewById(R.id.or_player_pause);
        handler = new Handler();

        or_player_play.setVisibility(View.GONE);

        mediaPlayer = new MediaPlayer();

         String audio1 = "https://firebasestorage.googleapis.com/v0/b/theguide-448e0.appspot.com/o/gandhiji_audio%20(mp3cut.net).mp3?alt=media&token=d6001a81-733e-489b-92af-fbaf2d986f7a";
         String audio2 = "https://firebasestorage.googleapis.com/v0/b/theguide-448e0.appspot.com/o/swamiji_audio.mp3?alt=media&token=dbea888a-5e77-430f-b970-376a2ba6d8fa";
         String audio3 = "https://firebasestorage.googleapis.com/v0/b/theguide-448e0.appspot.com/o/bose_audio.mp3?alt=media&token=d2ec5f7a-de32-41c1-9ebc-a98f9710aa05";

        or_back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(OTG_Result.this, com.mitadt.theguide.OTG.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                mediaPlayer.seekTo(0);
            }
        });

        String name,title,quote;

        name = getIntent().getStringExtra("name");
        title = getIntent().getStringExtra("title");
        quote = getIntent().getStringExtra("quote");


        if (name.equals("Mahatma Gandhi")) {
            or_name.setText(name);
            or_title.setText(title);
            or_quote.setText(quote);
            or_intro.setText(R.string.gandhiji);
            or_pic.setImageResource(R.drawable.gandhiji);
            or_player_name.setText(name);
            or_player_title.setText(title);

            or_read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Mahatma_Gandhi"));
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
                }
            });

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
                }
            });


            or_player_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.pause();
                    playCycle();
                    or_player_pause.setVisibility(View.GONE);
                    or_player_play.setVisibility(View.VISIBLE);
                }
            });


        }

        if (name.equals("Swami Vivekanand")) {
            or_name.setText(name);
            or_title.setText(title);
            or_quote.setText(quote);
            or_intro.setText(R.string.swamiji);
            or_pic.setImageResource(R.drawable.swamiji);
            or_player_name.setText(name);
            or_player_title.setText(title);

            or_read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Swami_Vivekananda"));
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
                }
            });

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
                }
            });


            or_player_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.pause();
                    playCycle();
                    or_player_pause.setVisibility(View.GONE);
                    or_player_play.setVisibility(View.VISIBLE);
                }
            });


        }

        if (name.equals("Subhas Chandra Bose")) {
            or_name.setText(name);
            or_title.setText(title);
            or_quote.setText(quote);
            or_intro.setText(R.string.bose);
            or_pic.setImageResource(R.drawable.bose);
            or_player_name.setText(name);
            or_player_title.setText(title);

            or_read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Subhas_Chandra_Bose"));
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
                }
            });

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
                }
            });


            or_player_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.pause();
                    playCycle();
                    or_player_pause.setVisibility(View.GONE);
                    or_player_play.setVisibility(View.VISIBLE);
                }
            });


        }


    }

    public void playCycle()
    {
        or_player_seek.setProgress(mediaPlayer.getCurrentPosition());

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
        Intent intent=new Intent(OTG_Result.this,com.mitadt.theguide.OTG.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
}
