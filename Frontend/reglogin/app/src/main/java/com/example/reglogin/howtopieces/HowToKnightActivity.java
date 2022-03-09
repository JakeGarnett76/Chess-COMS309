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

public class HowToKnightActivity extends Activity implements OnClickListener{

    private String TAG = HowToKnightActivity.class.getSimpleName();
    Button btnKnightBack;
    ImageView viewKnightMoves;
    TextView knightData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.htp_knight);

        btnKnightBack = (Button) findViewById(R.id.btnKnightBack);

        viewKnightMoves = (ImageView) findViewById(R.id.viewKnight);
        knightData = (TextView) findViewById(R.id.htpKnightText);

        btnKnightBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnKnightBack.getId()){
            startActivity(new Intent(HowToKnightActivity.this,
                    HowToPlayActivity.class));
        }
    }

}
