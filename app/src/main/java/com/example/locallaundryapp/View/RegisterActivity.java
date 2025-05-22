package com.example.locallaundryapp.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.credentials.CredentialManager;
import androidx.credentials.GetCredentialRequest;

import com.example.locallaundryapp.R;
import com.google.android.gms.common.SignInButton;
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption;

public class RegisterActivity extends AppCompatActivity {


    SignInButton google_login;

    CredentialManager credentialManager;
    GetCredentialRequest request;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        google_login = findViewById(R.id.google_login);
        credentialManager = CredentialManager.create(this);

        GetSignInWithGoogleOption googleOption = new GetSignInWithGoogleOption.Builder(getString(R.string.google_login_client_Id))
                .setNonce(java.util.UUID.randomUUID().toString())
                .build();

        request = new GetCredentialRequest.Builder()
                .addCredentialOption(googleOption)
                        .build();






        google_login.setOnClickListener(v -> {

//           google login here --------------------------

            requestGoogleLogin();

        });



    }

    private void requestGoogleLogin() {



    }
}