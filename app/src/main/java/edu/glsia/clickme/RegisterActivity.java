package edu.glsia.clickme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText nomEditText,pseudoEditext,prenomEditText;
    private Button btEnregistrer;
    private RadioButton radioButtonMasculin, radioButtonFeminin;
    private String feminin = "Féminin";
    private String masculin = "Masculin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

        applyPreferences();

        btEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register(nomEditText,pseudoEditext,prenomEditText);
            }
        });

        radioButtonMasculin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSexePreferences(radioButtonMasculin,masculin);
                radioButtonFeminin.setEnabled(false);
            }
        });

        radioButtonFeminin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSexePreferences(radioButtonFeminin,feminin);
                radioButtonMasculin.setEnabled(false);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initViews(){
        nomEditText = findViewById(R.id.nomEditText);
        pseudoEditext = findViewById(R.id.pseudoEditText);
        prenomEditText = findViewById(R.id.prenomEditText);
        btEnregistrer = findViewById(R.id.btEnregistrer);
        radioButtonFeminin =findViewById(R.id.radioButtonFeminin);
        radioButtonMasculin = findViewById(R.id.radioButtonMasculin);
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

    public void savePrenomPreferences(EditText editText){
        SharedPreferences prenomSharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prenomSharedPref.edit();
        editor.putString("nom",editText.getText().toString());
        editor.apply();
    }

    public void saveSexePreferences(RadioButton radioButton,String text){
        SharedPreferences sexeSharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sexeSharedPref.edit();
        editor.putString("sexe",text);
        editor.apply();
        radioButton.setChecked(true);
    }


    public void applyPreferences(){
        SharedPreferences pseudoSharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String lcp = pseudoSharedPreferences.getString("pseudo","floflo");

        SharedPreferences nomSharedPref = getPreferences(Context.MODE_PRIVATE);
        String lecompteur =  nomSharedPref.getString("nom","flo");

        SharedPreferences prenomSharedPref = getPreferences(Context.MODE_PRIVATE);
        String prenomCpt = prenomSharedPref.getString("nom","fly");

        SharedPreferences sexeSharedPref = getPreferences(Context.MODE_PRIVATE);
        String sexeCompt = sexeSharedPref.getString("sexe","flu");


        if(!lcp.equals("floflo") && !lecompteur.equals("flo") && !prenomCpt.equals("fly") && !sexeCompt.equals("flu")){
            pseudoEditext.setText(String.valueOf(lcp));
            nomEditText.setText(String.valueOf(lecompteur));
            prenomEditText.setText(String.valueOf(prenomCpt));

            String val =  String.valueOf(sexeCompt);

            if(val.equals("Féminin")){
                radioButtonFeminin.setChecked(true);
                radioButtonMasculin.setEnabled(false);
            }else if(val.equals("Masculin")){
                radioButtonMasculin.setChecked(true);
                radioButtonFeminin.setEnabled(false);
            }

            nomEditText.setEnabled(false);
            pseudoEditext.setEnabled(false);
            prenomEditText.setEnabled(false);
            btEnregistrer.setEnabled(false);
        }

    }

    public void register(EditText nomEditText,EditText pseudoEditext,EditText prenomEditText){
        String nomText = (nomEditText.getText()).toString();
        String pseudoText = (pseudoEditext.getText()).toString();
        String prenomText =(prenomEditText.getText()).toString();

        if(nomText.equals("") && pseudoText.equals("") && prenomText.equals("")){
            Toast.makeText(this,"Veuillez remplir les champs",Toast.LENGTH_LONG).show();
        } else if(nomText.equals("")){
            Toast.makeText(this,"Le champ nom est vide",Toast.LENGTH_LONG).show();
        }else if (pseudoText.equals("")){
            Toast.makeText(this,"Le champ pseudo est vide",Toast.LENGTH_LONG).show();
        }else if (prenomText.equals("")){
            Toast.makeText(this,"Le champ prenom est vide",Toast.LENGTH_LONG).show();
        }else {
            saveNomPreferences(nomEditText);
            savePseudoPreferences(pseudoEditext);
            savePrenomPreferences(prenomEditText);
        }
    }
}