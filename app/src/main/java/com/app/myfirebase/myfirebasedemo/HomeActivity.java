package com.app.myfirebase.myfirebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import entity.User;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewShowEmail;
    private Button btnLogout;
    private FirebaseAuth firebaseAuth;
    private EditText mEditTextName;
    private EditText mEditTextContact;
    private EditText mEditTextAddress;
    private EditText mEditTextAge;
    private Button mButtonSaveInfo;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextContact = (EditText) findViewById(R.id.editTextContact);
        mEditTextAddress = (EditText) findViewById(R.id.editTextAddress);
        mEditTextAge = (EditText) findViewById(R.id.editTextAge);
        mButtonSaveInfo = (Button) findViewById(R.id.buttonSaveInfo);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference();
        textViewShowEmail = (TextView) findViewById(R.id.textViewShowEmail);
        textViewShowEmail.setText("Welcome "+firebaseAuth.getCurrentUser().getEmail());

        mButtonSaveInfo.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == btnLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
        if(view == mButtonSaveInfo){
            saveUserInfo();
        }
    }

    //Save user info
    public void saveUserInfo(){

        String name = mEditTextName.getText().toString().trim();
        String contact = mEditTextContact.getText().toString().trim();
        String address = mEditTextAddress.getText().toString().trim();
        int age = Integer.parseInt(mEditTextAge.getText().toString().trim());

        User user = new User(name,contact,address,age);
        mDatabaseReference.child("User").setValue(user);
        mDatabaseReference.child("UserInfo").setValue(user);

    }
}
