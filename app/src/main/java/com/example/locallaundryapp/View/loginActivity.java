package com.example.locallaundryapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.locallaundryapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {


    TextView name, gmail;
    Button logout;
    ImageView profile_image;

    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);



        name = findViewById(R.id.user_name);
        gmail = findViewById(R.id.user_gmail);
        logout = findViewById(R.id.logout_button);
        profile_image = findViewById(R.id.profile_image);

        firebaseAuth = FirebaseAuth.getInstance();






    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
//            there is user login

        }else {
//             There is no any user login here
            startActivity(new Intent(loginActivity.this, RegisterActivity.class));
            finish();

        }
    }
}