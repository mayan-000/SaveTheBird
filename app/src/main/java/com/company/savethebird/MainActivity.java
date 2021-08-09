package com.company.savethebird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView bird, enemy1, enemy2, enemy3, coin, volume;
    private Button startButton;
    private Animation animation;
    private MediaPlayer mediaPlayer;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bird = findViewById(R.id.birdImageViewMain);
        enemy1 = findViewById(R.id.enemy1ImageViewMain);
        enemy2 = findViewById(R.id.enemy2ImageViewMain);
        enemy3 = findViewById(R.id.enemy3ImageViewMain);
        coin = findViewById(R.id.coinImageViewMain);
        volume = findViewById(R.id.volumeButton);
        startButton = findViewById(R.id.startGameButton);


        Glide.with(this)
                .load(R.drawable.flying)
                .into(bird);

        Glide.with(this)
                .load(R.drawable.enemy1)
                .into(enemy1);

        Glide.with(this)
                .load(R.drawable.enemy2)
                .into(enemy2);

        Glide.with(this)
                .load(R.drawable.enemy3)
                .into(enemy3);

        Glide.with(this)
                .load(R.drawable.coin)
                .into(coin);

        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim
        .scale_animation);

        startButton.setOnClickListener(v -> {
            mediaPlayer.reset();
            volume.setImageResource(R.drawable.volume_up);

            Intent i = new Intent(this, GameActivity.class);
            startActivity(i);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.start();

        volume.setOnClickListener(v -> {
            if(!flag){
                mediaPlayer.setVolume(0,0);
                volume.setImageResource(R.drawable.volume_off);
                flag = true;
            }
            else{
                mediaPlayer.setVolume(1,1);
                volume.setImageResource(R.drawable.volume_up);
                flag = false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.reset();
    }
}