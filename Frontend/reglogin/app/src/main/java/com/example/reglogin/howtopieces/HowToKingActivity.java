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

public class HowToKingActivity extends Activity implements OnClickListener {

    private String TAG = HowToKingActivity.class.getSimpleName();
    Button btnKingBack;
    ImageView viewKingMoves;
    TextView kingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.htp_king);

        btnKingBack = (Button) findViewById(R.id.btnKingBack);

        viewKingMoves = (ImageView) findViewById(R.id.viewKing);
        kingData = (TextView) findViewById(R.id.htpKingText);

        btnKingBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnKingBack.getId()){
            startActivity(new Intent(HowToKingActivity.this,
                    HowToPlayActivity.class));
        }
    }

}
