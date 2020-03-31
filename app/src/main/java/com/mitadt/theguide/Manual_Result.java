package com.mitadt.theguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class Manual_Result extends AppCompatActivity {

    ImageView pic;
    TextView name,title,quote,intro,player_name,player_title,read,audio;
    SeekBar player_seek;
    ImageView player_play,player_pause;
    LinearLayout player,back;

    ProgressDialog load;

    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;

    String dname,dtitle,dquote;
    int dpic;

    Animation di,fo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual__result);

        pic=findViewById(R.id.pic);
        intro=findViewById(R.id.intro);
        title=findViewById(R.id.title);
        quote=findViewById(R.id.quote);
        name=findViewById(R.id.name);
        player_name=findViewById(R.id.player_name);
        player_title=findViewById(R.id.player_title);
        read=findViewById(R.id.read);
        audio=findViewById(R.id.audio);
        player_seek=findViewById(R.id.player_seek);
        player_play=findViewById(R.id.player_play);
        player_pause=findViewById(R.id.player_pause);
        player=findViewById(R.id.player);
        back=findViewById(R.id.back);
        handler=new Handler();
        di= AnimationUtils.loadAnimation(this,R.anim.push_down_in);
        fo=AnimationUtils.loadAnimation(this,R.anim.fadeout);

        player.setVisibility(View.GONE);
        player_play.setVisibility(View.GONE);

        dpic=getIntent().getIntExtra("pic",0);
        dname=getIntent().getStringExtra("name");
        dtitle=getIntent().getStringExtra("title");
        dquote=getIntent().getStringExtra("quote");

        mediaPlayer=new MediaPlayer();

        final String audio1="https://firebasestorage.googleapis.com/v0/b/theguide-448e0.appspot.com/o/gandhiji_audio%20(mp3cut.net).mp3?alt=media&token=d6001a81-733e-489b-92af-fbaf2d986f7a";
        final String audio2="https://firebasestorage.googleapis.com/v0/b/theguide-448e0.appspot.com/o/swamiji_audio.mp3?alt=media&token=dbea888a-5e77-430f-b970-376a2ba6d8fa";
        final String audio3="https://firebasestorage.googleapis.com/v0/b/theguide-448e0.appspot.com/o/bose_audio.mp3?alt=media&token=d2ec5f7a-de32-41c1-9ebc-a98f9710aa05";
        if(dpic==1)
        {
            name.setText(dname);
            title.setText(dtitle);
            quote.setText(dquote);
            intro.setText(getResources().getString(R.string.gandhiji));
            player_name.setText(dname);
            player_title.setText(dtitle);
            pic.setImageResource(R.drawable.gandhiji);

            read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Mahatma_Gandhi"));
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
                }
            });

            audio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AsyncTask<Void,Void,Void>()
                    {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            load=ProgressDialog.show(Manual_Result.this,"","Loading...",true);
                            audio.setVisibility(View.GONE);
                            player.setVisibility(View.VISIBLE);

                            audio.clearAnimation();
                            player.clearAnimation();

                            audio.startAnimation(fo);
                            player.startAnimation(di);
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {

                            try {
                                mediaPlayer.setDataSource(audio1);
                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        player_seek.setMax(mediaPlayer.getDuration());
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
                        }
                    }.execute();


                   player_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(final MediaPlayer mp) {
                    mp.seekTo(0);
                    mp.start();
                }
            });

            player_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                    playCycle();
                    player_play.setVisibility(View.GONE);
                    player_pause.setVisibility(View.VISIBLE);
                }
            });


            player_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.pause();
                    playCycle();
                    player_pause.setVisibility(View.GONE);
                    player_play.setVisibility(View.VISIBLE);
                }
            });
        }

        if(dpic==2)
        {
            name.setText(dname);
            title.setText(dtitle);
            quote.setText(dquote);
            intro.setText(getResources().getString(R.string.swamiji));
            player_name.setText(dname);
            player_title.setText(dtitle);
            pic.setImageResource(R.drawable.swamiji);

            read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Swami_Vivekananda"));
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
                }
            });

            audio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AsyncTask<Void,Void,Void>()
                    {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            load=ProgressDialog.show(Manual_Result.this,"","Loading...",true);
                            audio.setVisibility(View.GONE);
                            player.setVisibility(View.VISIBLE);

                            audio.clearAnimation();
                            player.clearAnimation();

                            audio.startAnimation(fo);
                            player.startAnimation(di);
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {

                            try {
                                mediaPlayer.setDataSource(audio2);
                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        player_seek.setMax(mediaPlayer.getDuration());
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
                        }
                    }.execute();


                    player_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(final MediaPlayer mp) {
                    mp.seekTo(0);
                    mp.start();
                }
            });

            player_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                    playCycle();
                    player_play.setVisibility(View.GONE);
                    player_pause.setVisibility(View.VISIBLE);
                }
            });


            player_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.pause();
                    playCycle();
                    player_pause.setVisibility(View.GONE);
                    player_play.setVisibility(View.VISIBLE);
                }
            });
        }

        if(dpic==3)
        {
            name.setText(dname);
            title.setText(dtitle);
            quote.setText(dquote);
            intro.setText(getResources().getString(R.string.bose));
            player_name.setText(dname);
            player_title.setText(dtitle);
            pic.setImageResource(R.drawable.bose);

            read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Subhas_Chandra_Bose"));
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
                }
            });

            audio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AsyncTask<Void,Void,Void>()
                    {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            load=ProgressDialog.show(Manual_Result.this,"","Loading...",true);
                            audio.setVisibility(View.GONE);
                            player.setVisibility(View.VISIBLE);

                            audio.clearAnimation();
                            player.clearAnimation();

                            audio.startAnimation(fo);
                            player.startAnimation(di);
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {

                            try {
                                mediaPlayer.setDataSource(audio3);
                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        player_seek.setMax(mediaPlayer.getDuration());
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
                        }
                    }.execute();


                    player_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(final MediaPlayer mp) {
                 mp.seekTo(0);
                 mp.start();
                }
            });

            player_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                    playCycle();
                    player_play.setVisibility(View.GONE);
                    player_pause.setVisibility(View.VISIBLE);
                }
            });


            player_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.pause();
                    playCycle();
                    player_pause.setVisibility(View.GONE);
                    player_play.setVisibility(View.VISIBLE);
                }
            });
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Manual_Result.this,com.mitadt.theguide.Manual.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);

                mediaPlayer.seekTo(0);
            }
        });



    }

    public void playCycle()
    {
        player_seek.setProgress(mediaPlayer.getCurrentPosition());

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
        if(player_play.getVisibility()==View.VISIBLE)
        {
            player_play.setVisibility(View.GONE);
            player_pause.setVisibility(View.VISIBLE);
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
}
