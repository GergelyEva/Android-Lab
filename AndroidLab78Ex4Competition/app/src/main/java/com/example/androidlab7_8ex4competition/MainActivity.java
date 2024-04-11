package com.example.androidlab7_8ex4competition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SHOW_PARTICIPANTS = 1;

    ImageView menu;
    LinearLayout layoutmenu;
    Button addh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = findViewById(R.id.menuimg);
        layoutmenu = findViewById(R.id.linearMenu);
        layoutmenu.setVisibility(View.GONE);

        addh = findViewById(R.id.addh);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutmenu.setVisibility(View.VISIBLE);
            }
        });

        addh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParticipantHandler.class);
                startActivity(intent);
            }
        });
    }
}