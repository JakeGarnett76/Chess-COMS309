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

public class HowToPawnActivity extends Activity implements OnClickListener {

    private String TAG = HowToPawnActivity.class.getSimpleName();
    Button btnPawnBack;
    ImageView viewPawnMoves;
    TextView pawnData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.htp_pawn);

        btnPawnBack = (Button) findViewById(R.id.btnPawnBack);

        viewPawnMoves = (ImageView) findViewById(R.id.viewPawn);
        pawnData = (TextView) findViewById(R.id.htpPawnText);

        btnPawnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnPawnBack.getId()){
            startActivity(new Intent(HowToPawnActivity.this,
                    HowToPlayActivity.class));
        }
    }
}
