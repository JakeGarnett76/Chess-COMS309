package com.example.reglogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener {

    private String TAG = HomeActivity.class.getSimpleName();
    Button btnPlay, btnHowTo, btnSettings, btnLeaderboard, btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnHowTo = (Button) findViewById(R.id.btnHowTo);
        btnSettings = (Button) findViewById(R.id.btnSettings);
        btnLeaderboard = (Button) findViewById(R.id.btnLeaderboard);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        btnPlay.setOnClickListener(this);
        btnHowTo.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnLeaderboard.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnPlay){
            startActivity(new Intent(HomeActivity.this,
                    CreateJoinRoomActivity.class));
        }
        else if(v.getId() == R.id.btnSettings){
            System.out.println("Settings Screen selected, tba");
            startActivity(new Intent(HomeActivity.this,
                    SettingsActivity.class));
        }
        else if(v.getId() == R.id.btnLeaderboard){
            System.out.println("Leaderboard Screen selected, tba");
        }
        else if(v.getId() == R.id.btnHowTo){
            startActivity(new Intent(HomeActivity.this,
                    HowToPlayActivity.class));
        }
        else if(v.getId() == R.id.btnLogOut){
            startActivity(new Intent(HomeActivity.this,
                    LoginActivity.class));
        }

    }
}
