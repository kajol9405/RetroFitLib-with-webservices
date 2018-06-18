package com.example.kajol.myapplication;

/**
 * Created by Kajol on 3/20/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kajol.myapplication.network.ApiClient;
import com.example.kajol.myapplication.network.ApiInterface;
import com.example.kajol.myapplication.network.Login;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(email, password);

                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter all details!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void checkLogin(String mail, String pass) {
        pDialog.setMessage("Logging in ...");
        showDialog();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> responseCall = apiService.LOGIN_CALL(mail, pass);

        responseCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, retrofit2.Response<Login> response) {
                hideDialog();
                final Login response1 = response.body();
                if (response.code() == 200) {
                    if (!response1.isError()) {
                        Login.UserEntity user = response1.getUser();
                        String name=user.getName();
                        String email=user.getEmail();

                        if(user.getIsBuyer()==1) {
                            Intent i = new Intent(LoginActivity.this,
                                    BuyerHomeActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("Email",email);
                            i.putExtras(bundle);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Intent i = new Intent(LoginActivity.this,
                                    SellerHomeActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("Sellername",name);
                            bundle.putString("Email",email);
                            i.putExtras(bundle);
                            startActivity(i);
                            finish();

                        }
                        Log.e(TAG, "onResponse:===Success! ");

                    } else {
                        Log.e(TAG, "onResponse: " + response1.getErrorMsg());
                    }
                } else {
                    Log.e(TAG, "error code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                hideDialog();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}