package com.example.reglogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WaitingRoomActivity extends Activity implements OnClickListener {

    private String TAG = WaitingRoomActivity.class.getSimpleName();
    Button btnBack, btnStart;
    TextView roomID, roomCode, playerOne, playerTwo, gamemode;

    int playerCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_room);

        btnBack = (Button) findViewById(R.id.btnLeaveRoom);
        btnStart = (Button) findViewById(R.id.btnStart);

        roomID = (TextView) findViewById(R.id.roomID);
        roomCode = (TextView) findViewById(R.id.roomCode);
        playerOne = (TextView) findViewById(R.id.playerOne);
        playerTwo = (TextView) findViewById(R.id.playerTwo);
        gamemode = (TextView) findViewById(R.id.gamemode);

        //Starts at one, since someone had to make the room
        playerCount = 1;

        btnBack.setOnClickListener(this);
        btnStart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStart){
            if(playerCount == 2){
                startActivity(new Intent(WaitingRoomActivity.this,
                        TwoPlayerChessboard.class));
            }
            else{
                System.out.println("Not enough players!");
            }
        }
        else if(v.getId() == R.id.btnLeaveRoom){
            startActivity(new Intent(WaitingRoomActivity.this,
                    HomeActivity.class));
        }
    }
}
