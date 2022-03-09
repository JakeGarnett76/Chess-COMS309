package com.example.reglogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This class is the starting point of the Advanced Chess app.
 * It contains a simple start screen with an image (TBA) and a simple start button,
 * taking the user to the login screen.
 *
 * @author Theron Gale
 */
public class MainActivity extends Activity implements OnClickListener {
    Button btnEnter;

    /**
     * Creates an instance where the start screen and start button will be initialized
     * @param savedInstanceState The state from which the start screen will be made.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnter = (Button) findViewById(R.id.btnEnterRequest);

        btnEnter.setOnClickListener(this);
    }

    /**
     * A click listener that when the start button is clicked/tapped, it will send the user to the login screen.
     * @param v The spot where the user has clicked/tapped
     */
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btnEnterRequest){
            startActivity(new Intent(MainActivity.this,
                    TwoPlayerChessboard.class));
        }
    }
}