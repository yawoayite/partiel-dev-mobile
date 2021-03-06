package edu.glsia.clickme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

import edu.glsia.clickme.database.DBHelper;
import edu.glsia.clickme.model.Score;
import edu.glsia.clickme.repository.ScoreRepository;

public class GameActivity extends AppCompatActivity {

    private ArrayList<String> maListe = new ArrayList<String>();
    private String X ="x";
    private String Y = "y";
    private String Z = "z";
    private String ancienneValeur;
    private int score = 0;
    private int compteur ;

    private DBHelper dbHelper;

    private Button buttonUn,buttonDeux,buttonTrois,buttonQuatre,buttonCinq,buttonSix,buttonSept,buttonHuit,buttonNeuf;
    private EditText scoreEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        compteur = 0 ;
        dbHelper = new DBHelper(this);

        initButtons();

        listeInitializer(maListe);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initOnClick();

    }

    public void initButtons(){
        buttonUn  = findViewById(R.id.buttonUn);
        buttonDeux = findViewById(R.id.buttonDeux);
        buttonTrois = findViewById(R.id.buttonTrois);
        buttonQuatre = findViewById(R.id.buttonQuatre);
        buttonCinq = findViewById(R.id.buttonCinq);
        buttonSix = findViewById(R.id.buttonSix);
        buttonSept = findViewById(R.id.buttonSept);
        buttonHuit = findViewById(R.id.buttonHuit);
        buttonNeuf = findViewById(R.id.buttonNeuf);

        scoreEditText = findViewById(R.id.scoreEditText);
    }

    public void initOnClick(){
        onClick(buttonUn);
        onClick(buttonDeux);
        onClick(buttonTrois);
        onClick(buttonQuatre);
        onClick(buttonCinq);
        onClick(buttonSix);
        onClick(buttonSept);
        onClick(buttonHuit);
        onClick(buttonNeuf);
    }

    public void onClick(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(button,maListe);
                compteur++;
                saveScore(compteur);
            }
        });
    }

    public void listeInitializer(ArrayList arrayList){
        arrayList.add(X);
        arrayList.add(Y);
        arrayList.add(Z);
    }


    public String getVal(ArrayList arrayList){
        Random random = new Random();
        int position = random.nextInt(arrayList.size());
        String  val = (String)arrayList.get(position);
        return val;
    }

    public void setButtonText(Button button, ArrayList arrayList){
        button.setText(getVal(arrayList));
        button.setEnabled(false);
        if(ancienneValeur == button.getText().toString())
            score+=1;
        ancienneValeur= button.getText().toString();
        scoreEditText.setText(String.valueOf(score));
    }

    public void saveScore(int value){
        if(value == 9){
            Score scoreObject = new Score();
            scoreObject.setScore(String.valueOf(score));
            scoreObject.setDate();

            ScoreRepository scoreRepository = new ScoreRepository();
            scoreRepository.save(dbHelper.getDatabase(),scoreObject);
            finish();
        }
    }

}