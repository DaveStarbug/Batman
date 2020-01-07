package com.star.batman.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.star.batman.R;

import tyrantgit.explosionfield.ExplosionField;

public class SplashActivity extends Activity {

    ExplosionField m_explosionFiled;
    ImageView imgIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        m_explosionFiled=ExplosionField.attach2Window(this);

        BindView();

        StartMainActivity();

    }

    private void BindView()
    {
        imgIcon=findViewById(R.id.imgIcon);
    }

    private void StartMainActivity()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                m_explosionFiled.explode(imgIcon);
                m_explosionFiled.postOnAnimation(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

            }
        },3000);
    }
}
