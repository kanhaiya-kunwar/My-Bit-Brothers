package com.example.mybitbrothers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText signupName,mobileNumber,emailId,password,confirmPassword;
    Button signUpBtn,goToLoginPageBtn;

    // Firebase variables
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupName = findViewById(R.id.signup_name);
        mobileNumber = findViewById(R.id.signup_number);
        emailId = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        confirmPassword = findViewById(R.id.signup_confirm_password);

        signUpBtn = findViewById(R.id.signup_btn);
        goToLoginPageBtn = findViewById(R.id.login_page_going_btn);

        // Sign In button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = signupName.getText().toString();
                String mobileNum = mobileNumber.getText().toString();
                String email = emailId.getText().toString();
                String pass = password.getText().toString();
                String conPass = confirmPassword.getText().toString();

                if (!userName.isEmpty()){
                    signupName.setError(null);
                    if (!mobileNum.isEmpty()){
                        mobileNumber.setError(null);
                        if (!email.isEmpty()){
                            emailId.setError(null);
                            if (!pass.isEmpty()){
                                password.setError(null);
                                if (!conPass.isEmpty()){
                                    confirmPassword.setError(null);
                                    // Validating all the fields of sign up page!!

                                    if (pass.equals(conPass)){
                                        // Confirm Password Matching

                                        if (email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){

                                            firebaseDatabase = FirebaseDatabase.getInstance();
                                            reference = firebaseDatabase.getReference("datauser");

                                            // Getting all the values
                                            String userName_s = signupName.getText().toString();
                                            String mobileNum_s = mobileNumber.getText().toString();
                                            String email_s = emailId.getText().toString();
                                            String pass_s = password.getText().toString();
                                            String conPass_s = confirmPassword.getText().toString();

                                            //Creating object of the class for getters or setters
                                            storingSignupData storingSignupData = new storingSignupData(userName_s,mobileNum_s,email_s,pass_s,conPass_s);

                                            //storing
                                            reference.child(mobileNum_s).setValue(storingSignupData);

                                            Toast.makeText(getApplicationContext(),"Register Successfully",Toast.LENGTH_SHORT).show();

                                            // Moving to Main Activity
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            finish(); // bcoz  when we press back button then close the app not moved to signup page.


                                        }else{
                                            emailId.setError("Invalid Email id");
                                        }
                                    }else{
                                        confirmPassword.setError("Password doesn't match");
                                    }
                                }else{
                                    confirmPassword.setError("Please enter confirm password");
                                }
                            }else{
                                password.setError("Please enter password");
                            }
                        }else{
                            emailId.setError("Please enter email id");
                        }
                    }else{
                        mobileNumber.setError("Please enter mobile number");
                    }
                }else{
                    signupName.setError("Please enter username");
                }

            }
        });

        // Go to Login page button
        goToLoginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(intent);
            }
        });

    }
}