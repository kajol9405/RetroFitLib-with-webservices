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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kajol.myapplication.network.ApiClient;
import com.example.kajol.myapplication.network.ApiInterface;
import com.example.kajol.myapplication.network.Register;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private CheckBox isBuyer;
    private CheckBox isSeller;
    int isBuyerChecked = 0;
    int isSellerChecked = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        inputFullName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        isBuyer=(CheckBox)findViewById(R.id.checkbox_buyer);
        isSeller=(CheckBox)findViewById(R.id.checkbox_seller);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                String name = inputFullName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && (isBuyerChecked==1 || isSellerChecked==1)) {
                    registerUser1(name, email, password, isBuyerChecked,isSellerChecked);
                    Toast.makeText(getApplicationContext(),
                            "check====="+isBuyerChecked+"  "+isSellerChecked, Toast.LENGTH_LONG)
                            .show();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        isBuyer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                isBuyer.setChecked(b);
                if(b==true)
                {
                    isBuyerChecked = 1;
                }
                else
                {
                    isBuyerChecked = 0;

                }
            }
        });

        isSeller.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                isSeller.setChecked(b);
                if(b==true)
                {
                    isSellerChecked = 1;
                }
                else
                {
                    isSellerChecked = 0;

                }
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */


    private void registerUser1(final String name, final String email,
                              final String password, final int isBuyerChecked, final int isSellerChecked)
    {
        pDialog.setMessage("Registering...");
        showDialog();
        Toast.makeText(getApplicationContext(),
                "check====="+isBuyerChecked+" "+isSellerChecked, Toast.LENGTH_LONG)
                .show();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> responseCall = apiService.REGISTER_CALL(name,email,password,isBuyerChecked,isSellerChecked);

        // works in background process so use enqueue
        responseCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, retrofit2.Response<Register> response) {
                hideDialog();
                final Register response1 = response.body();
                if (response.code() == 200) {
                    if (!response1.isError()) {
                        Register.UserEntity user = response1.getUser();

                        Log.e(TAG, "onResponse:=====success=== " + user.getIsBuyer());
                        Log.e(TAG, "onResponse: " + user.getName());
                       // Log.e(TAG, "onResponse: " + user.getName());
                    } else {
                        Log.e(TAG, "onResponse: " + response1.getErrorMsg());
                    }
                } else {
                    Log.e(TAG, "error code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
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