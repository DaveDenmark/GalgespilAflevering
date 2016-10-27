package com.example.id.galgespilaflevering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class hovedmenu extends Activity implements View.OnClickListener {

        Button LaunchGame, Highscore, HelpText, QuitGame;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedmenu);

            LaunchGame = (Button) findViewById(R.id.LaunchGame);
            Highscore = (Button) findViewById(R.id.Highscore);
            HelpText = (Button) findViewById(R.id.HelpText);
            QuitGame = (Button) findViewById(R.id.QuitGame);

            LaunchGame.setOnClickListener(this);
            Highscore.setOnClickListener(this);
            HelpText.setOnClickListener(this);
            QuitGame.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
            if (v == LaunchGame) {
                Intent i = new Intent(this, GameLaunch.class);
                startActivity(i);
            }
            else if (v == Highscore) {
                Intent i = new Intent(this, Score.class);
                startActivity(i);
            }
            else if (v == HelpText) {
                Intent i = new Intent(this, HelpView.class);
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
