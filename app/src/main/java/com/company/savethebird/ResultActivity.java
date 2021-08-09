package com.company.savethebird;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView score;
    private Button btn;
    private TextView high, annouce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = findViewById(R.id.ScoreView);
        btn = findViewById(R.id.button);
        high = findViewById(R.id.highscore);
        annouce = findViewById(R.id.annouce);

        int Score = getIntent().getIntExtra("Score",0);

        score.setText("YOUR SCORE : "+Score);

        SharedPreferences sharedPreferences = getSharedPreferences("saveData", MODE_PRIVATE);

        int highScore = sharedPreferences.getInt("HighScore",0);

        high.setText("HIGHSCORE : "+highScore);

        if(Score>=500){
            annouce.setText("YOU HAVE WON THE GAME!!!");
            high.setText("HIGHSCORE :"+Score);
        }
        else{
            annouce.setText("SORRY! YOU HAVE LOST THE GAME.");
        }
        if(highScore<=Score){
            high.setText("HIGHSCORE :"+Score);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("HighScore",Score);
            editor.apply();
        }

        btn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Help The Innocent Bird")
                .setMessage("Are you sure you want to quit the game?")
                .setCancelable(false)
                .setNegativeButton("Quit Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                })
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create().show();
    }
}