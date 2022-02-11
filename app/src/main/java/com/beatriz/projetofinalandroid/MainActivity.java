package com.beatriz.projetofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Check List");

        RadioButton OK;
        RadioButton NOK;

            OK = findViewById(R.id.OK);
            NOK = findViewById(R.id.NOK);
    }
}