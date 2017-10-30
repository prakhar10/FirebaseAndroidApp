package com.app.myfirebase.myfirebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewShowEmail;
    private Button btnLogout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        textViewShowEmail = (TextView) findViewById(R.id.textViewShowEmail);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        textViewShowEmail.setText("Welcome "+firebaseAuth.getCurrentUser().getEmail());

        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == btnLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
    }
}
