package com.example.reglogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends Activity implements OnClickListener {

    private String TAG = ResultsActivity.class.getSimpleName();
    Button btnResultsBack;
    TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        btnResultsBack = (Button) findViewById(R.id.btnResultsBack);

        winner = (TextView) findViewById(R.id.winner);

        btnResultsBack.setOnClickListener(this);
    }

    //TODO
    //put leaderboard stats

    @Override
    public void onClick(View v) {
        if(v.getId() == btnResultsBack.getId()){
            startActivity(new Intent(ResultsActivity.this,
                    HomeActivity.class));
        }
    }
}
