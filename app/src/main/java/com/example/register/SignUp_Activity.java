package com.example.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText et_Email , et_Password , et_confirmPassword ;
    Button Register ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        et_Email = findViewById(R.id.ET_Email) ;
        et_Password = findViewById(R.id.ET_Password) ;
        et_confirmPassword = findViewById(R.id.ET_ConfPass) ;


        Register = findViewById(R.id.btn_SignUp) ;
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterCode() ;
            }    });

    }



    private void RegisterCode() {

        String email = et_Email.getText().toString();
        String password = et_Password.getText().toString();
        String confirmPassword = et_confirmPassword.getText().toString();

        if (email.isEmpty())                  { et_Email.setError("Error"); }
        else if (password.isEmpty())          { et_Password.setError("Error"); }
        else if (confirmPassword.isEmpty())   { et_confirmPassword.setError("Error"); }
        else {
            // Sign up Code
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(SignUp_Activity.this, "Email Is Created...", Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUp_Activity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }


    }



}