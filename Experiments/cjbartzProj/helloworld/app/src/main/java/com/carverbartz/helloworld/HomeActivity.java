package com.carverbartz.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    Button b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent i2 = getIntent();
        String txt = i2.getStringExtra(MainActivity.EXTRA_TEXT);

        TextView tv = (TextView) findViewById(R.id.TheText);
        tv.setText(txt);

        b3 = (Button) findViewById(R.id.buttonBack);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
