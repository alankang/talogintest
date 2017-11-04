package com.example.alan.talogintest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by alan on 11/3/17.
 */

public class LoginActivity extends AppCompatActivity {


    private EditText txtemailinput, txtpwinput;
    private Button btnlogin, btncreate;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FirebaseAuth.getInstance().signOut();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("UserEmail",txtemailinput.getText().toString());

                    startActivity(intent);


                }
            }
        };


        btnlogin = (Button)findViewById(R.id.btnlogin);
        btncreate = (Button)findViewById(R.id.btncreate);
        txtemailinput =  (EditText)findViewById(R.id.txtemailinput);
        txtpwinput =  (EditText)findViewById(R.id.txtpwinput);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignin();
            }
        });

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,CreateAccountActivity.class);
                startActivity(intent);
                finish();


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignin(){
        String email = txtemailinput.getText().toString();
        String password = txtpwinput.getText().toString();


        if(TextUtils.isEmpty(email)||(TextUtils.isEmpty(password))){
            Toast.makeText(LoginActivity.this, "Some field is blank!", Toast.LENGTH_LONG).show();

        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Unsuccessful Sign In", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }



    }
}
