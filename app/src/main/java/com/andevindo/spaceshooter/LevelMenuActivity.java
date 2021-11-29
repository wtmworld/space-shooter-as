package com.andevindo.spaceshooter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class LevelMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mEasy, mNormal, mHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);

        //Membuat tampilan menjadi full screen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        //Membuat tampilan selalu menyala jika activity aktif
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mEasy = findViewById(R.id.easy);
        mNormal = findViewById(R.id.normal);
        mHard = findViewById(R.id.hard);

        mEasy.setOnClickListener(this);
        mNormal.setOnClickListener(this);
        mHard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int level;
        switch (v.getId()) {
            case R.id.easy:
                level = 1;
                break;
            case R.id.normal:
                level = 2;
                break;
            case R.id.hard:
                level = 3;
                break;
            default:
                level = 1;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
        finish();
    }
}
