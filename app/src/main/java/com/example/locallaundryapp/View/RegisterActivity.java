package com.example.locallaundryapp.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.locallaundryapp.R;
import com.example.locallaundryapp.View.AppBodySection.MainBodyActivity;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class RegisterActivity extends AppCompatActivity {




    TextInputEditText EdName,EdNumber,EdGmail,EdPassword;
    Button register_button;




//    gogole ============================
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

//        my Data bass require field Introduce ====
        EdName = findViewById(R.id.EdName);
        EdNumber = findViewById(R.id.EdNumber);
        EdGmail = findViewById(R.id.EdGmail);
        EdPassword = findViewById(R.id.EdPassword);

        register_button = findViewById(R.id.register_button);



        // =========================================


        register_button.setOnClickListener(v -> {

            register_button.setText("Please Wait...");

            String name= EdName.getText().toString();
            String number= EdNumber.getText().toString();
            String gmail= EdGmail.getText().toString();
            String password= EdPassword.getText().toString();
            String profile_pic = "https//:profile.com/pic";
            String role = "0";



            String url = "https://angoflow.com/local_loundry_app_php_server/connection.php?n=" + name+"&nb="+ number +"&g="+ gmail + "&p="+ password +"&pp="+ profile_pic + "&r="+ role;

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
//                  When Success ==================================
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Successfully Profile Create")
                            .setMessage(response)
                            .show();

                    register_button.setText("Account Created");
                    startActivity(new Intent(RegisterActivity.this, MainBodyActivity.class));
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    When Error ===================================
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Something went wrong!")
                            .setMessage(error.toString())
                            .show();



                }
            });


            if (EdName.length()>0 || EdNumber.length()>0 || EdGmail.length()>0 || EdPassword.length()>0|| profile_pic.length()>0){
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }else {
                EdName.setText("Enter Your Name");
                EdNumber.setText("Enter Your Number");
                EdGmail.setText("Enter Your Gmail");
                EdPassword.setText("Enter Your password");

            }



        });



//        Google firebase and Auth===============================================


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