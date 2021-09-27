package com.example.mybitbrothers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    EditText signinNumber,signinPassword;
    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signinNumber = findViewById(R.id.signin_Number);
        signinPassword = findViewById(R.id.signin_password);

        signInBtn=findViewById(R.id.signin_btn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signinNumber.getText().toString();
                String password = signinPassword.getText().toString();

                if (!email.isEmpty()){
                    signinNumber.setError(null);
                    if (! password.isEmpty()){
                        signinPassword.setError(null);

                        //firebase connectivity
                        final String email_data = signinNumber.getText().toString();
                        final String password_data = signinPassword.getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");

                        // query for validating input mobile number with database mobile number
                        Query check_email = databaseReference.orderByChild("mobileNumber").equalTo(email_data);

                        check_email.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists()){
                                    signinNumber.setError(null);
                                    String checkPassword = snapshot.child(email_data).child("password").getValue(String.class);
                                    if (checkPassword.equals(password_data)){
                                        signinPassword.setError(null);
                                        Toast.makeText(getApplicationContext(),"Login Succesfully",Toast.LENGTH_SHORT).show();
                                        //Moving to main activity after successful login
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }else{
                                        signinPassword.setError("Wrong Password");
                                    }
                                }else{
                                    signinNumber.setError("Number doesn't match");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });





                    }else{
                        signinPassword.setError("Please enter password");
                    }

                }else{
                    signinNumber.setError("Please enter Email");
                }

            }
        });
    }
}