package com.example.kajol.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kajol.myapplication.network.ApiClient;
import com.example.kajol.myapplication.network.ApiInterface;
import com.example.kajol.myapplication.network.ProductDisplay;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Kajol on 3/23/2017.
 */
public class BuyerHomeActivity extends Activity {

    TextView productsName;
    private TextView info;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_home);
        btnLogout=(Button)findViewById(R.id.btnLogout);
        productsName=(TextView)findViewById(R.id.productNames);
        info=(TextView)findViewById(R.id.UserType);
        Intent in = getIntent();
        //Getting bundle
        Bundle b = in.getExtras();
        //Getting data from bundle
        final String email = b.getString("Email");
        final StringBuilder s=new StringBuilder();
        info.setText("Welcome User!!\n \n The List of Products are: ");
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerHomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductDisplay> responseCall = apiService.PRODUCT_DISPLAY_CALL(email);
        responseCall.enqueue(new Callback<ProductDisplay>() {
            @Override
            public void onResponse(Call<ProductDisplay> call, retrofit2.Response<ProductDisplay> response) {

                final ProductDisplay response1 = response.body();
                if (response.code() == 200) {
                    if (!response1.isError()) {
                        List<ProductDisplay.ResultEntity> product = response1.getResult();
                        for (ProductDisplay.ResultEntity resultEntity : product) {

                            s.append(resultEntity.getProductname().toString()+"\n\n");
                            productsName.setText(s);
                            Log.e("BuyerHomeActResponse", "onResponse:===Success " + resultEntity.getUniqueProductId());
                        }

                    } else {
                        Log.e("BuyerHomeActResponse", "onResponse: Error==" + response1.isError());
                    }
                } else {
                    Log.e("BuyerHomeActResponse", "error code : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ProductDisplay> call, Throwable t) {
                //  hideDialog();
                Log.e("BuyerHomeActResponse", "onFailure: " + t.getMessage());
            }
        });



    }

}
