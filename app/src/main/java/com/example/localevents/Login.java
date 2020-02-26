package com.example.localevents;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity
{
    EditText txtUsername, txtPassword;
    Button btnSignIn;
    TextView tvRegister;
    FirebaseAuth fAuth;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsernameR);
        txtPassword = findViewById(R.id.txtPasswordR);
        tvRegister = findViewById(R.id.tvRegister);
        btnSignIn = findViewById(R.id.btnSignIn);

        fAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(username))
                {
                    txtUsername.setError("Username is required.");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    txtPassword.setError("Password is required");
                    return;
                }
                if(password.length() < 6)
                {
                    txtPassword.setError("Password length must be greater than or equals to 6 characters.");
                    return;
                }

                fAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }

}
