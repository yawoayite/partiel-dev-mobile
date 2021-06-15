package edu.glsia.clickme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.glsia.clickme.adapter.ScoreAdapter;
import edu.glsia.clickme.database.DBHelper;
import edu.glsia.clickme.model.Score;
import edu.glsia.clickme.repository.ScoreRepository;

public class ScoreActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    private Button buttonRechercher;
    private EditText rechercherEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        buttonRechercher = findViewById(R.id.buttonRechercher);
        rechercherEditText = findViewById(R.id.rechercherEditText);

        dbHelper =  new DBHelper(this);
        ScoreRepository scoreRepository = new ScoreRepository();

        ListView listView = findViewById(R.id.scoreListView);
        List<Score>scores = new ArrayList<>();

        scores.addAll(scoreRepository.findAll(dbHelper.getDatabase()));
        ScoreAdapter scoreAdapter = new ScoreAdapter(scores,this);
        listView.setAdapter(scoreAdapter);
        scoreAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        buttonRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textRecherche = rechercherEditText.getText().toString();
            }
        });
    }
}