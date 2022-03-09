package com.carverbartz.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.carverbartz.helloworld.EXTRA_TEXT";

    Button b1, b2;
   // TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.buttonMessage);
        b2 = (Button) findViewById(R.id.buttonNext);
       // tv1 = (TextView) findViewById(R.id.TheText);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Hello World", Toast.LENGTH_LONG).show();
               // tv1.setVisibility(View.VISIBLE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 = (EditText) findViewById(R.id.edittext);
                String text = editText1.getText().toString();
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                i.putExtra(EXTRA_TEXT, text);
                startActivity(i);

            }
        });
    }
}
