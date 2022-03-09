package com.example.reglogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateJoinRoomActivity extends Activity implements OnClickListener{

    private String TAG = CreateJoinRoomActivity.class.getSimpleName();
    Button btnCreateRoom, btnJoinRoom, btnCJRBack;
    EditText roomCode;
    TextView roomError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_join_room);

        btnCreateRoom = (Button) findViewById(R.id.btnCreateRoom);
        btnJoinRoom = (Button) findViewById(R.id.btnJoinRoom);
        btnCJRBack = (Button) findViewById(R.id.btnCJRBack);

        btnCreateRoom.setOnClickListener(this);
        btnJoinRoom.setOnClickListener(this);
        btnCJRBack.setOnClickListener(this);

        roomCode = (EditText) findViewById(R.id.enterGameID);

        roomError = (TextView) findViewById(R.id.roomCodeError);
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == btnCreateRoom.getId()){
            startActivity(new Intent(CreateJoinRoomActivity.this,
                    WaitingRoomActivity.class));
        }
        else if(v.getId() == btnCJRBack.getId()){
            startActivity(new Intent(CreateJoinRoomActivity.this,
                    HomeActivity.class));
        }
        else if(v.getId() == btnJoinRoom.getId()){
            String roomID = roomCode.getText().toString();

            if(roomID.length() != 6){
                roomError.setText("Code not long enough");
            }
            else{
                //Player count check
                //Board exists check
                startActivity(new Intent(CreateJoinRoomActivity.this,
                        WaitingRoomActivity.class));
            }
        }

    }

}
