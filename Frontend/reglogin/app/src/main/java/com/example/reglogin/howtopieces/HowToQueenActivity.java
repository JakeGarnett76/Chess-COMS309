package com.example.reglogin.howtopieces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reglogin.HowToPlayActivity;
import com.example.reglogin.R;

public class HowToQueenActivity extends Activity implements OnClickListener {

    private String TAG = HowToQueenActivity.class.getSimpleName();
    Button btnQueenBack;
    ImageView viewQueenMoves;
    TextView queenData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.htp_queen);

        btnQueenBack = (Button) findViewById(R.id.btnQueenBack);

        viewQueenMoves = (ImageView) findViewById(R.id.viewQueen);
        queenData = (TextView) findViewById(R.id.htpQueenText);

        btnQueenBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnQueenBack.getId()){
            startActivity(new Intent(HowToQueenActivity.this,
                    HowToPlayActivity.class));
        }
    }

}
