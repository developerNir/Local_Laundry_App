package com.example.locallaundryapp.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.locallaundryapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URI;

public class loginActivity extends AppCompatActivity {


    TextView TvName, TvGmail;
    Button logout_button;
    ImageView profile_image;

    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);



        TvName = findViewById(R.id.user_name);
        TvGmail = findViewById(R.id.user_gmail);
        logout_button = findViewById(R.id.logout_button);
        profile_image = findViewById(R.id.profile_image);

        firebaseAuth = FirebaseAuth.getInstance();




        logout_button.setOnClickListener(v -> {
            firebaseAuth.signOut();

            startActivity(new Intent(this, RegisterActivity.class));
            finish();

        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
//            there is user login
            String name = firebaseUser.getDisplayName();
            String gmail = firebaseUser.getEmail();
            Uri image = firebaseUser.getPhotoUrl();


            TvName.setText(name);
            TvGmail.setText(gmail);
            Glide.with(this).load(image).into(profile_image);


        }else {
//             There is no any user login here
            startActivity(new Intent(loginActivity.this, RegisterActivity.class));
            finish();

        }
    }
}