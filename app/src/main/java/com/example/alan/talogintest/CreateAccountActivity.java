package com.example.alan.talogintest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by alan on 11/3/17.
 */

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = CreateAccountActivity.class.getSimpleName();


//    @Bind(R.id.createUserButton) Button mCreateUserButton;
//    @Bind(R.id.nameEditText) EditText mNameEditText;
//    @Bind(R.id.emailEditText) EditText mEmailEditText;
//    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
//    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
//    @Bind(R.id.loginTextView) TextView mLoginTextView;
    private FirebaseAuth mAuth;

    private Button btnSU;
    private EditText txtName, txtEmail, txtPW, txtCPW;
    private TextView txtlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnSU = (Button)findViewById(R.id.btnSU);
        txtName = (EditText)findViewById(R.id.txtName);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPW = (EditText)findViewById(R.id.txtPW);
        txtCPW = (EditText)findViewById(R.id.txtCPW);
        txtlogin = (TextView)findViewById(R.id.txtlogin);


        txtlogin.setOnClickListener(this);
        btnSU.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {

        if (view == txtlogin) {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == btnSU) {
            createNewUser();
        }

    }
    private void createNewUser() {
        final String name = txtName.getText().toString().trim();
        final String email = txtEmail.getText().toString().trim();
        String password = txtPW.getText().toString().trim();
        String confirmPassword = txtCPW.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Authentication successful");
                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
