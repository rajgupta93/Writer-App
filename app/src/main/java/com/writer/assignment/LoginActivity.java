package com.writer.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static android.content.ContentValues.TAG;


public class LoginActivity extends AppCompatActivity {
    TextSwitcher mtextswitcher;
    TextView mskiplogin;
    String textToShow[] = {"Hello", " नमस्ते ", "Hola!","ਸਤਿ ਸ਼੍ਰੀ ਅਕਾਲ","Vanakkam","Bonjour","Olá"};
    int currentIndex=0;
    int RC_SIGN_IN=123;
    private GoogleSignInClient mGoogleSignInClient;
    ImageView googlesign;
    private FirebaseAuth mAuth;



    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for changing status bar icon colors

//
//        Handler h = new Handler();
//        mtextswitcher.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//            }
//        }, 400);

        mAuth=FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
                mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }



        
        setContentView(R.layout.activity_login);

        mskiplogin=findViewById(R.id.skipsignin);
        mskiplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), viewer.class);
                startActivity(intent);
                finish();
            }
        });
        googlesign = findViewById(R.id.googlesignin);

        mtextswitcher = findViewById(R.id.hello);
        mtextswitcher.setInAnimation(getApplicationContext(), android.R.anim.slide_in_left);
        mtextswitcher.setOutAnimation(getApplicationContext(), android.R.anim.slide_out_right);

        googlesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        new CountDownTimer(180000, 3000) {
            public void onTick(long millisUntilFinished) {


                // If index reaches maximum reset it
                if (currentIndex == 6) {
                    currentIndex = 0;
                }
                currentIndex++;
                Log.e("text to print "," "+ textToShow[currentIndex]);
                mtextswitcher.setCurrentText((textToShow[currentIndex]));

            }

            public void onFinish() {
                mtextswitcher.setCurrentText((textToShow[0])); }

        }.start();

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }
    private void updateUI(FirebaseUser user) {
          Intent i = new  Intent(getApplicationContext(),viewer.class);
          startActivity(i);
          finish();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG,"firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
                Toast.makeText(getApplicationContext(),"Sign in ",Toast.LENGTH_LONG);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(getApplicationContext(),"Sign failed ",Toast.LENGTH_LONG);

            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"signInWithCredential:success",Toast.LENGTH_LONG);
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });

    }

}
