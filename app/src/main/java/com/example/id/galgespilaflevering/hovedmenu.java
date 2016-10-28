package com.example.id.galgespilaflevering;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;


public class hovedmenu extends Activity implements View.OnClickListener {

        Button LaunchGame, QuitGame;
        ImageView imageView, imageView2;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedmenu);


            LaunchGame = (Button) findViewById(R.id.LaunchGame);
            LaunchGame.setText("Start spil");
            LaunchGame.setTextColor(Color.parseColor("#ff0000ff"));

            QuitGame = (Button) findViewById(R.id.QuitGame);
            QuitGame.setText("Afslut spil");
            QuitGame.setTextColor(Color.parseColor("#ff0000ff"));


            imageView = (ImageView) findViewById(R.id.imageView2);
            imageView.setImageResource(R.drawable.forkert6);

            imageView2 = (ImageView) findViewById(R.id.imageView3);
            imageView2.setImageResource(R.drawable.galgespil);

            LaunchGame.setOnClickListener(this);
            QuitGame.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
            if (v == LaunchGame) {
                Intent i = new Intent(this, GameLaunch.class);
                startActivity(i);
            }
            else if (v == QuitGame) {
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }
    }
