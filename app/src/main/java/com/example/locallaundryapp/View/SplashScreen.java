package com.example.locallaundryapp.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.example.locallaundryapp.R;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // full screen show -------------------------------

        // how to app system ber hide ==========================================
        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        // Configure the behavior of the hidden system bars.
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );

        // Hide both the status bar and the navigation bar.
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());









        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // sharePreference --------------------------------------
                sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

                Boolean firstTimeGet = sharedPreferences.getBoolean("firstTime", true);


                if (firstTimeGet){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("firstTime", false);
                    editor.apply();

                    startActivity(new Intent(SplashScreen.this, OnBoarding.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashScreen.this, loginActivity.class));
                    finish();
                }


            }
        }, 3000);





    }
}