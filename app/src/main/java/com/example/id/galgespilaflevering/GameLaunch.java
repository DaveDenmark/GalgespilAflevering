package com.example.id.galgespilaflevering;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.id.galgespilaflevering.logik.Galgelogik;

public class GameLaunch extends Activity implements View.OnClickListener {

    Galgelogik logik = new Galgelogik();
    private TextView Info;
    private Button Play;
    private EditText TextInsert;
    private ImageView imagestatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_launch);

        Info = (TextView) findViewById(R.id.InfoTekst);
        Play = (Button) findViewById(R.id.button);
        TextInsert = (EditText) findViewById(R.id.editText);


        imagestatus = (ImageView) findViewById(R.id.imageView);
        imagestatus.setImageResource(R.drawable.galge);


        Info.setOnClickListener(this);
        Play.setOnClickListener(this);
        TextInsert.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == Play) {
            String bogstav = TextInsert.getText().toString();
            if (bogstav.length() != 1) {
                TextInsert.setError("Skriv præcis ét bogstav");
                return;
            }
            logik.gætBogstav(bogstav);
            TextInsert.setText("");
            TextInsert.setError(null);
        }
        imagecheck();
        opdaterSkærm();
    }
    private void opdaterSkærm() {
        Info.setText("Gæt ordet: " + logik.getSynligtOrd());
        Info.append("\n\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte:" + logik.getBrugteBogstaver());

        if (logik.erSpilletVundet()) {
            Info.append("\nDu har vundet");
        }
        if (logik.erSpilletTabt()) {
            Info.setText("Du har tabt, ordet var : " + logik.getOrdet());
        }
    }
        private void imagecheck() {
            if (logik.getAntalForkerteBogstaver() == 1)
                imagestatus.setImageResource(R.drawable.forkert1);

            else if (logik.getAntalForkerteBogstaver() == 2)
            imagestatus.setImageResource(R.drawable.forkert2);

            else if (logik.getAntalForkerteBogstaver() == 3)
                imagestatus.setImageResource(R.drawable.forkert3);

            else if (logik.getAntalForkerteBogstaver() == 4)
                imagestatus.setImageResource(R.drawable.forkert4);

            else if (logik.getAntalForkerteBogstaver() == 5)
                imagestatus.setImageResource(R.drawable.forkert5);

            else if (logik.getAntalForkerteBogstaver() == 6)
                imagestatus.setImageResource(R.drawable.forkert6);
        }

    }
