package com.example.reglogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.reglogin.howtopieces.HowToBishopActivity;
import com.example.reglogin.howtopieces.HowToKingActivity;
import com.example.reglogin.howtopieces.HowToKnightActivity;
import com.example.reglogin.howtopieces.HowToPawnActivity;
import com.example.reglogin.howtopieces.HowToQueenActivity;
import com.example.reglogin.howtopieces.HowToRookActivity;

public class HowToPlayActivity extends Activity implements OnClickListener{

    private String TAG = HowToPlayActivity.class.getSimpleName();
    Button btnBack;
    ImageView htpPawn, htpRook, htpBishop, htpKnight, htpQueen, htpKing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_play);

        btnBack = (Button) findViewById(R.id.btnHTPBack);

        htpPawn = (ImageView) findViewById(R.id.howtoPawn);
        htpRook = (ImageView) findViewById(R.id.howtoRook);
        htpBishop = (ImageView) findViewById(R.id.howtoBishop);
        htpKnight = (ImageView) findViewById(R.id.howtoKnight);
        htpQueen = (ImageView) findViewById(R.id.howtoQueen);
        htpKing = (ImageView) findViewById(R.id.howtoKing);

        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnHTPBack){
            startActivity(new Intent(HowToPlayActivity.this,
                    HomeActivity.class));
        }
        else if(v.getId() == htpPawn.getId()){
            System.out.println("Pawn");
            startActivity(new Intent(HowToPlayActivity.this,
                    HowToPawnActivity.class));
        }
        else if(v.getId() == htpRook.getId()){
            System.out.println("Rook");
            startActivity(new Intent(HowToPlayActivity.this,
                    HowToRookActivity.class));
        }
        else if(v.getId() == htpBishop.getId()){
            System.out.println("Bishop");
            startActivity(new Intent(HowToPlayActivity.this,
                    HowToBishopActivity.class));
        }
        else if(v.getId() == htpKnight.getId()){
            System.out.println("Knight");
            startActivity(new Intent(HowToPlayActivity.this,
                    HowToKnightActivity.class));
        }
        else if(v.getId() == htpQueen.getId()){
            System.out.println("Queen");
            startActivity(new Intent(HowToPlayActivity.this,
                    HowToQueenActivity.class));
        }
        else if(v.getId() == htpKing.getId()){
            System.out.println("King");
            startActivity(new Intent(HowToPlayActivity.this,
                    HowToKingActivity.class));
        }
    }

}
