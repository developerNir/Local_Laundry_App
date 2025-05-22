package com.example.locallaundryapp.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;

import com.example.locallaundryapp.R;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class RegisterActivity extends AppCompatActivity {


    SignInButton google_login;

    CredentialManager credentialManager;
    GetCredentialRequest request;
    FirebaseAuth nAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        google_login = findViewById(R.id.google_login);
        credentialManager = CredentialManager.create(this);
        nAuth = FirebaseAuth.getInstance();




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


        credentialManager.getCredentialAsync(this, request, null, Runnable::run, new CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() {
            @Override
            public void onResult(GetCredentialResponse getCredentialResponse) {



                Credential credential = getCredentialResponse.getCredential();

                GoogleIdTokenCredential googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.getData());

                String IdToken = googleIdTokenCredential.getIdToken();

                Toast.makeText(getApplicationContext(), "Please wait...", Toast.LENGTH_SHORT).show();

                AuthCredential authCredential = GoogleAuthProvider.getCredential(IdToken, null);

                nAuth.signInWithCredential(authCredential)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
//                                when success it going to main activity
                                startActivity(new Intent(RegisterActivity.this, loginActivity.class));
                                finish();
                            }
                        });



            }

            @Override
            public void onError(@NonNull GetCredentialException e) {

            }
        });

    }
}