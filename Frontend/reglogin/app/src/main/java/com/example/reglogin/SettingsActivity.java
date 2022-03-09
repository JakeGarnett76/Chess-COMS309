package com.example.reglogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends Activity implements View.OnClickListener {

    private String TAG = SettingsActivity.class.getSimpleName();
    Button btnBack;
    TextView phSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        btnBack = (Button) findViewById(R.id.btnSettingsBack);

        phSettings = (TextView) findViewById(R.id.placeholderSettings);

        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSettingsBack){
            startActivity(new Intent(SettingsActivity.this,
                    HomeActivity.class));
        }
    }
}
