package edu.glsia.jeupartiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText nomEditText,pseudoEditext;
    private Button btEnregistrer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nomEditText = findViewById(R.id.nomEditText);
        pseudoEditext = findViewById(R.id.pseudoEditText);
        btEnregistrer = findViewById(R.id.btEnregistrer);

        applyPreferences();

        btEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(nomEditText,pseudoEditext);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void saveNomPreferences(EditText editText){
        SharedPreferences nomSharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = nomSharedPref.edit();
        editor.putString("nom",editText.getText().toString());
        editor.apply();
    }

    public void savePseudoPreferences(EditText editText){
        SharedPreferences pseudoSharedPref =  getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pseudoSharedPref.edit();
        editor.putString("pseudo",editText.getText().toString());
        editor.apply();
    }


    public void applyPreferences(){
        SharedPreferences pseudoSharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String lcp = pseudoSharedPreferences.getString("pseudo","floflo");
        if(!lcp.equals("floflo")){
            pseudoEditext.setText(String.valueOf(lcp));
        }

        SharedPreferences nomSharedPref = getPreferences(Context.MODE_PRIVATE);
        String lecompteur =  nomSharedPref.getString("nom","flo");
        if(!lecompteur.equals("flo")){
            nomEditText.setText(String.valueOf(lecompteur));
        }

    }

    public void register(EditText nomEditText,EditText pseudoEditext){
        String nomText = (nomEditText.getText()).toString();
        String pseudoText = (pseudoEditext.getText()).toString();

        if(nomText.length() == 0){
            Toast.makeText(this,"Le champ nom est vide",Toast.LENGTH_SHORT).show();
        }else if (pseudoText.length() == 0){
            Toast.makeText(this,"Le champ pseudo est vide",Toast.LENGTH_SHORT).show();
        }else {
            saveNomPreferences(nomEditText);
            savePseudoPreferences(pseudoEditext);
        }
    }
}