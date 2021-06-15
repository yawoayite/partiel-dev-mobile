package edu.glsia.clickme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater  =  getMenuInflater();
        inflater.inflate(R.menu.menu_accueil,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuButtonScore:
                goToScorePage();
                return true;
            case R.id.menuButtonJouer:
                goToGamePage();
                return true;
            case R.id.meunButtonEnregistrer:
                goToRegistrerPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToScorePage(){
        Intent  scoreIntent = new Intent(getBaseContext(),ScoreActivity.class);
        startActivity(scoreIntent);
    }

    public void goToGamePage(){
        Intent gameIntent = new Intent(getBaseContext(),GameActivity.class);
        startActivity(gameIntent);
    }

    public void goToRegistrerPage(){
        Intent registerIntent = new Intent(getBaseContext(),RegisterActivity.class);
        startActivity(registerIntent);
    }


}