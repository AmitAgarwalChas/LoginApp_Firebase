package com.example.login_page_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {

    TextView signin;
    TextInputEditText mname;
    TextInputEditText memail;
    TextInputEditText mpassword;
    TextInputEditText mconfirmpass;
    TextInputEditText mphone;
    RadioGroup rg;
    Button register;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mname = (TextInputEditText) findViewById(R.id.full_name);
        memail = (TextInputEditText) findViewById(R.id.signup_email);
        mpassword = (TextInputEditText) findViewById(R.id.signup_password);
        mconfirmpass = (TextInputEditText)findViewById(R.id.confirm_password);
        mphone = (TextInputEditText)findViewById(R.id.signup_phone);
        rg = (RadioGroup)findViewById(R.id.mfo);
        register = (Button)findViewById(R.id.signup_account);

        fAuth = FirebaseAuth.getInstance();
        register.setEnabled(false);
        confirmInput();
        backToLogin();


    }

    private boolean validateName(){
        String nameInput = mname.getText().toString().trim();
        if(nameInput.isEmpty()){
            mname.setError("Name can't be empty");
            return false;
        }
        else{
            mname.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String emailInput = memail.getText().toString().trim();
        if(emailInput.isEmpty()){
            memail.setError("Email required");
            return false;
        }
        else{
            memail.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordInput = mpassword.getText().toString().trim();
        if(passwordInput.isEmpty()){
            mpassword.setError("Password can't be empty");
            return false;
        }
        else if(passwordInput.length()<6){
            mpassword.setError("Password length must be between 6 and 15");
            return false;
        }
        else if(passwordInput.length()>15){
            mpassword.setError("Password length must be between 6 and 15");
            return false;
        }
        else{
            mpassword.setError(null);
            return true;
        }
    }

    private boolean confirmPassword(){
        String passwordInput = mpassword.getText().toString().trim();
        String confirmpass = mconfirmpass.getText().toString().trim();
        if(confirmpass.isEmpty()){
            mconfirmpass.setError("Password can't be empty");
            return false;
        }
        else if(confirmpass.equals(passwordInput)){
            mconfirmpass.setError(null);
            return true;
        }
        else{
            mconfirmpass.setError("Password not matched");
            return false;
        }
    }

    private boolean validatePhone(){
        String phoneInput = mphone.getText().toString().trim();
        if(phoneInput.isEmpty()){
            mphone.setError("Phone number required");
            return false;
        }
        else{
            mphone.setError(null);
            return true;
        }
    }

    private boolean validateGender(){
        if (rg.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getApplicationContext(), "Please select your Gender",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }
    public void confirmInput(){
        if(!validateName() | !validateEmail() | !validatePassword() |
                !validatePhone() | !confirmPassword() | !validateGender()){

        }
        else {
            register.setEnabled(true);
        }

    }

    public void backToLogin(){
        signin = (TextView)findViewById(R.id.signup_login);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sign_up.this , MainActivity.class));
            }
        });
    }
}
