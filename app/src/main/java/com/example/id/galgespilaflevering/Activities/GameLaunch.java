package com.example.id.galgespilaflevering.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Color;
import android.widget.TextView;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import com.example.id.galgespilaflevering.R;
import com.example.id.galgespilaflevering.logik.Galgelogik;

public class GameLaunch extends Activity implements View.OnClickListener {

    Galgelogik logik = new Galgelogik();
    private TextView Info;
    private Button Play, HelpText, Nytspil;
    private EditText TextInsert;
    private ImageView imagestatus;
    Galgelogik galgelogik;
    ProgressDialog pd;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_launch);
        galgelogik = new Galgelogik();
        SharedPreferences game_startup =
                PreferenceManager.getDefaultSharedPreferences(this);
        int intValue = game_startup.getInt("intValue", 0);
        SharedPreferences.Editor editor = game_startup.edit();
        editor.putInt("intValue", ++intValue);
        editor.commit();

        Info = (TextView) findViewById(R.id.InfoTekst);
        Info.setText("Start spil ved skrive ét bogstav og tryk dernæst gæt");
        Info.setTextColor(Color.parseColor("#ff0000ff"));


        Play = (Button) findViewById(R.id.button);
        Play.setText("Gæt");
        Play.setTextColor(Color.parseColor("#ff0000ff"));

        Nytspil = (Button) findViewById(R.id.button3);
        Nytspil.setText("Nyt Spil");
        Nytspil.setTextColor(Color.parseColor("#ff0000ff"));

        HelpText = (Button) findViewById(R.id.button2);
        HelpText.setTextColor(Color.parseColor("#ff0000ff"));


        TextInsert = (EditText) findViewById(R.id.editText);
        TextInsert.setTextColor(Color.parseColor("#ff0000ff"));


        imagestatus = (ImageView) findViewById(R.id.imageView);
        imagestatus.setImageResource(R.drawable.galge);

        Nytspil.setOnClickListener(this);
        Info.setOnClickListener(this);
        Play.setOnClickListener(this);
        TextInsert.setOnClickListener(this);
        HelpText.setOnClickListener(this);

        //sætter bagrundsfarve
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

    }

    //Sætter tilbage knappen til den aktivitet jeg vil have.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(GameLaunch.this, hovedmenu.class));
        finish();
    }

    public void getAsyncWords() {
        pd = ProgressDialog.show(this, "Vent", "Et øjeblik, henter data...");
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {galgelogik.hentOrdFraDr();
                    return "Ordene hentet fra DRs Server";
                } catch (Exception e) {
                    //System.out.print(StackTraceElement.class);
                    return "Ordene blev ikke hentet korrekt";
                }
            }

            @Override
            protected void onPostExecute(Object result) {
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }

        } .execute();
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
            imagecheck();
            opdaterSkærm();
            System.out.println(galgelogik.getOrdet());
        }

        else if (v == HelpText) {
            Intent i = new Intent(this, HelpView.class);
            startActivity(i);
        }
        else if (v == Nytspil) {
            getAsyncWords();
            logik.nulstil();
            opdaterSkærm();

        }
    }
    private void opdaterSkærm() {
        Info.setText("Gæt ordet: " + logik.getSynligtOrd());
        Info.append("\n\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte:" + logik.getBrugteBogstaver());

        if (logik.erSpilletTabt()) {
            Info.setText("Du har tabt, ordet var: " + logik.getOrdet());
        }

        if (logik.erSpilletVundet()) {
            Info.append("\nDu har vundet");

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