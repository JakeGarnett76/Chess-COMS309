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

public class HowToRookActivity extends Activity implements OnClickListener {

    private String TAG = HowToRookActivity.class.getSimpleName();
    Button btnRookBack;
    ImageView viewRookMoves;
    TextView rookData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.htp_rook);

        btnRookBack = (Button) findViewById(R.id.btnRookBack);

        viewRookMoves = (ImageView) findViewById(R.id.viewRook);
        rookData = (TextView) findViewById(R.id.htpRookText);

        btnRookBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnRookBack.getId()){
            startActivity(new Intent(HowToRookActivity.this,
                    HowToPlayActivity.class));
        }
    }

}
