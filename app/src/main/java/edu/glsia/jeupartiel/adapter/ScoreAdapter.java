package edu.glsia.jeupartiel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;
import edu.glsia.jeupartiel.R;
import edu.glsia.jeupartiel.model.Score;

public class ScoreAdapter extends BaseAdapter {
    private List<Score> scoreList;
    private Context context ;

    public ScoreAdapter(){}

    public ScoreAdapter(List scoreList,Context context){
        this.context = context;
        this.scoreList = scoreList;
    }

    @Override
    public int getCount() {
        return scoreList.size();
    }

    @Override
    public Object getItem(int position) {
       return scoreList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return ((Score)this.getItem(position)).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScoreHolder scoreHolder = new ScoreHolder();

        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_score_item, null);
        else scoreHolder = (ScoreHolder) convertView.getTag();

        scoreHolder.score = convertView.findViewById(R.id.textscore);
        scoreHolder.date = convertView.findViewById(R.id.textdate);

        scoreHolder.score.setText(((Score) getItem(position)).getScore());
        scoreHolder.date.setText(((Score) getItem(position)).getDate());

        convertView.setTag(scoreHolder);
        return convertView;
    }

    static class ScoreHolder{
        TextView score;
        TextView date;
    }
}
