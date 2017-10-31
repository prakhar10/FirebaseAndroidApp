package com.app.myfirebase.myfirebasedemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnRegister;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private TextView mTextViewSignIn;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        if(mFirebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }

        mBtnRegister = (Button) findViewById(R.id.btnRegister);
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mTextViewSignIn = (TextView) findViewById(R.id.textViewSignIn);

        mBtnRegister.setOnClickListener(this);
        mTextViewSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == mBtnRegister){
            registerUser(view);
        }
        if(view  == mTextViewSignIn){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
    }
    //Method will register the user into the system
    private void registerUser(View view){

        String email = mEditTextEmail.getText().toString().trim();
        String password = mEditTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(getApplicationContext(),"Enter your email id",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(getApplicationContext(),"Enter your password",Toast.LENGTH_LONG).show();
            return;
        }

        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"Could not register."+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
