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

public class HowToBishopActivity extends Activity implements OnClickListener {

    private String TAG = HowToBishopActivity.class.getSimpleName();
    Button btnBishopBack;
    ImageView viewBishopMoves;
    TextView bishopData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.htp_bishop);

        btnBishopBack = (Button) findViewById(R.id.btnBishopBack);

        viewBishopMoves = (ImageView) findViewById(R.id.viewBishop);
        bishopData = (TextView) findViewById(R.id.htpBishopText);

        btnBishopBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnBishopBack.getId()){
            startActivity(new Intent(HowToBishopActivity.this,
                    HowToPlayActivity.class));
        }
    }

}
