 package com.company.savethebird;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

 public class GameActivity extends AppCompatActivity {

    private ImageView bird, enemy1, enemy2, enemy3, coin1, coin2, right1, right2, right3;
    private TextView score, startGame;
    private ConstraintLayout constraintLayout;
    private boolean touchControl = false;
    private boolean beginControl = false;
    private Runnable runnable;
    private Handler handler;
    private int birdx, birdy, enemy1x, enemy1y, enemy2x, enemy2y, enemy3x, enemy3y,
     coin1x, coin1y, coin2x, coin2y;
    private int screenx, screeny;
    private int Score = 0, right = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bird = findViewById(R.id.birdGame);
        enemy1 = findViewById(R.id.enemy1Game);
        enemy2 = findViewById(R.id.enemy2Game);
        enemy3 = findViewById(R.id.enemy3Game);
        coin1 = findViewById(R.id.coin1Game);
        coin2 = findViewById(R.id.coin2Game);
        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);
        score = findViewById(R.id.scoreGame);
        startGame = findViewById(R.id.startGame);
        constraintLayout = findViewById(R.id.gameLayout);

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
                .into(coin1);
        Glide.with(this)
                .load(R.drawable.coin)
                .into(coin2);

        constraintLayout.setOnTouchListener((View.OnTouchListener) (v, event) -> {
            startGame.setVisibility(View.INVISIBLE);

            if(event.getAction()==MotionEvent.ACTION_DOWN){
                touchControl=true;
            }
            if(event.getAction()==MotionEvent.ACTION_UP){
                touchControl=false;
            }

            if(beginControl==false){
                beginControl=true;

                screenx = (int)constraintLayout.getWidth();
                screeny = (int)constraintLayout.getHeight();

                birdx = (int)bird.getX();
                birdy = (int)bird.getY();

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        moveToBird();
                        enemyControl();
                        collisionControl();
                    }
                };

                handler.post(runnable);
            }
            else{
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    touchControl=true;
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    touchControl=false;
                }
            }

            return true;
        });
    }

    public void moveToBird(){
        if(touchControl){
            birdy -= (screeny/50);
        }
        else{
            birdy += (screeny/50);
        }

        birdy=(birdy<=0?0:birdy);
        birdy=(bird.getHeight()+birdy>=screeny?screeny-bird.getHeight():birdy);

        bird.setY(birdy);
    }

    public void enemyControl(){
        enemy1.setVisibility(View.VISIBLE);
        enemy2.setVisibility(View.VISIBLE);
        enemy3.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);
        coin1.setVisibility(View.VISIBLE);


        enemy1x -= (screenx/150);

        if(Score>=50 && Score <100){
            enemy1x-=(screenx/130);
        }
        if(Score>=100 && Score<150){
            enemy1x-=(screenx/120);
        }
        if(Score>=150){
            enemy1x-=(screenx/100);
        }
        if(enemy1x+enemy1.getWidth()<=0){
            enemy1x = (screenx+200);
            enemy1y = (int)Math.floor(Math.random() * screeny);
            enemy1y=(enemy1y<=0?0:enemy1y);
            enemy1y=(enemy1.getHeight()+enemy1y>=screeny?screeny-enemy1.getHeight():enemy1y);
        }

        enemy1.setX(enemy1x);
        enemy1.setY(enemy1y);



        enemy2x -= (screenx/140);
        if(Score>=50 && Score <100){
            enemy2x-=(screenx/120);
        }
        if(Score>=100 && Score<150){
            enemy2x-=(screenx/110);
        }
        if(Score>=150){
            enemy2x-=(screenx/90);
        }
        if(enemy2x+enemy2.getWidth()<=0){
            enemy2x = (screenx+200);
            enemy2y = (int)Math.floor(Math.random() * screeny);
            enemy2y=(enemy2y<=0?0:enemy2y);
            enemy2y=(enemy2.getHeight()+enemy2y>=screeny?screeny-enemy2.getHeight():enemy2y);
        }

        enemy2.setX(enemy2x);
        enemy2.setY(enemy2y);


        enemy3x -= (screenx/130);
        if(Score>=50 && Score <100){
            enemy3x-=(screenx/110);
        }
        if(Score>=100 && Score<150){
            enemy3x-=(screenx/100);
        }
        if(Score>=150){
            enemy3x-=(screenx/80);
        }
        if(enemy3x+enemy3.getWidth()<=0){
            enemy3x = (screenx+200);
            enemy3y = (int)Math.floor(Math.random() * screeny);
            enemy3y=(enemy3y<=0?0:enemy3y);
            enemy3y=(enemy3.getHeight()+enemy3y>=screeny?screeny-enemy3.getHeight():enemy3y);
        }

        enemy3.setX(enemy3x);
        enemy3.setY(enemy3y);

        coin1x -= (screenx/120);

        if(coin1x+coin1.getWidth()<=0){
            coin1x = (screenx+200);
            coin1y = (int)Math.floor(Math.random() * screeny);
            coin1y=(coin1y<=0?0:coin1y);
            coin1y=(coin1.getHeight()+coin1y>=screeny?screeny-coin1.getHeight():coin1y);
        }

        coin1.setX(coin1x);
        coin1.setY(coin1y);

        coin2x -= (screenx/110);
        if(coin2x+coin2.getWidth()<=0){
            coin2x = (screenx+200);
            coin2y = (int)Math.floor(Math.random() * screeny);
            coin2y=(coin2y<=0?0:coin2y);
            coin2y=(coin2.getHeight()+coin2y>=screeny?screeny-coin2.getHeight():coin2y);
        }

        coin2.setX(coin2x);
        coin2.setY(coin2y);

    }

    public void collisionControl(){
        int centerEnemy1X = enemy1x + enemy1.getWidth()/2;
        int centerEnemy1Y = enemy1y + enemy1.getHeight()/2;

        if(centerEnemy1X >= birdx && centerEnemy1X <= birdx + bird.getWidth()
        && centerEnemy1Y >= birdy && centerEnemy1Y <= birdy + bird.getHeight()){
            enemy1x = screenx + 200;
            right--;
        }

        int centerEnemy2X = enemy2x + enemy2.getWidth()/2;
        int centerEnemy2Y = enemy2y + enemy2.getHeight()/2;

        if(centerEnemy2X >= birdx && centerEnemy2X <= birdx + bird.getWidth()
                && centerEnemy2Y >= birdy && centerEnemy2Y <= birdy + bird.getHeight()){
            enemy2x = screenx + 200;
            right--;
        }

        int centerEnemy3X = enemy3x + enemy3.getWidth()/2;
        int centerEnemy3Y = enemy3y + enemy3.getHeight()/2;

        if(centerEnemy3X >= birdx && centerEnemy3X <= birdx + bird.getWidth()
                && centerEnemy3Y >= birdy && centerEnemy3Y <= birdy + bird.getHeight()){
            enemy3x = screenx + 200;
            right--;
        }

        int centerCoin1X = coin1x + coin1.getWidth()/2;
        int centerCoin1Y = coin1y + coin1.getHeight()/2;

        if(centerCoin1X >= birdx && centerCoin1X <= birdx + bird.getWidth()
                && centerCoin1Y >= birdy && centerCoin1Y <= birdy + bird.getHeight()){
            coin1x = screenx + 200;
            Score+=10;
            score.setText(""+Score);
        }

        int centerCoin2X = coin2x + coin2.getWidth()/2;
        int centerCoin2Y = coin2y + coin2.getHeight()/2;

        if(centerCoin2X >= birdx && centerCoin2X <= birdx + bird.getWidth()
                && centerCoin2Y >= birdy && centerCoin2Y <= birdy + bird.getHeight()){
            coin2x = screenx + 200;
            Score+=10;
            score.setText(""+Score);
        }

        if(right>0 && Score<500){
            if(right==2){
                right1.setImageResource(R.drawable.empty_heart);
            }
            if(right==1){
                right2.setImageResource(R.drawable.empty_heart);
            }
            handler.postDelayed(runnable,20);
        }
        else if(Score>=500){
            handler.removeCallbacks(runnable);
            constraintLayout.setEnabled(false);
            startGame.setText("VUHU! YOU HAVE WON THE GAME");
            startGame.setVisibility(View.VISIBLE);
            enemy1.setVisibility(View.INVISIBLE);
            enemy2.setVisibility(View.INVISIBLE);
            enemy3.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);

            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    birdx+=(screenx/300);
                    bird.setX(birdx);
                    bird.setY(screeny/2);

                    if(birdx<=screenx){
                        handler.postDelayed(runnable,20);
                    }
                    else{
                        handler.removeCallbacks(runnable);
                        startActivity(new Intent(GameActivity.this, ResultActivity.class)
                                .putExtra("Score",Score));
                        finish();
                    }
                }
            };
            handler.post(runnable);
        }
        else if(right==0){
            handler.removeCallbacks(runnable);
            right3.setImageResource(R.drawable.empty_heart);
            startActivity(new Intent(GameActivity.this, ResultActivity.class)
            .putExtra("Score",Score));
            finish();
        }
    }


}