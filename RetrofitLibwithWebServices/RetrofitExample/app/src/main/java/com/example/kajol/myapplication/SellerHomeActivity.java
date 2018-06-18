package com.example.kajol.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kajol.myapplication.network.ApiClient;
import com.example.kajol.myapplication.network.ApiInterface;
import com.example.kajol.myapplication.network.StoreProduct;
import com.example.kajol.myapplication.network.UpdateProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Kajol on 3/22/2017.
 */
public class SellerHomeActivity extends Activity
{
    private int j;

    private TextView sellername;
    private String[] items,itemsId;
    private StringBuilder productnamesstr,productnamesupdatestr;
    private StringBuilder productnamesIdstr,productnamesIdupdatestr;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_home);

        Intent in = getIntent();

        //Getting bundle
        Bundle b = in.getExtras();
        //Getting data from bundle
        final String name = b.getString("Sellername");
        final String email = b.getString("Email");

        //Extract the data…
        sellername = (TextView) findViewById(R.id.UserType);

        final EditText et = (EditText) findViewById(R.id.noOfProducts);

        final EditText productidupdate = (EditText) findViewById(R.id.productidupdate);
        final EditText newproduct = (EditText) findViewById(R.id.newproductname);

        Button enter = (Button) findViewById(R.id.btnSubmitNo);
        Button logout = (Button) findViewById(R.id.btnLogout);
        final Button update = (Button) findViewById(R.id.updatebtn);
//        final ScrollView scrollView=(ScrollView)findViewById(R.id.scrollView);
        final LinearLayout ml = (LinearLayout) findViewById(R.id.mainll);
        final LinearLayout ml2 = (LinearLayout) findViewById(R.id.ml2);
        // final LinearLayout ul=(LinearLayout)findViewById(R.id.updateLL);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerHomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            public Button submit;
            List<EditText> productboxes = new ArrayList<EditText>();
            List<EditText> productboxesId = new ArrayList<EditText>();
            public EditText editTextid;
            public EditText editText;

            @Override
            public void onClick(View view) {
                j = Integer.parseInt(et.getText().toString());

                if (submit == null) {
                    LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    btnParams.setMargins(10, 10, 10, 10);
                    submit = new Button(SellerHomeActivity.this);
                    submit.setText("Submit all products");
                    submit.setLayoutParams(btnParams);

                    submit.setBackgroundResource(R.color.bg_login);
                    ml2.addView(submit);
                }

                for (int i = 0; i < j; i++) {
                  //  ScrollView sv = new ScrollView(SellerHomeActivity.this);
                 //   sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    ll = new LinearLayout(SellerHomeActivity.this);
                    ll.setOrientation(LinearLayout.HORIZONTAL);

                    LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    editTextParams.setMargins(10, 10, 10, 10);
                    editText = new EditText(SellerHomeActivity.this);
                    editText.setHint("Enter product name");
                    editText.setId(i);
                    editText.setPadding(5,5,5,5);
                    editText.setLayoutParams(editTextParams);
                    editText.setBackgroundResource(R.color.white);
                    productboxes.add(editText);
                    ll.addView(editText);

                    editTextid = new EditText(SellerHomeActivity.this);
                    editTextid.setHint("Enter product id");
                    editTextid.setPadding(5,5,5,5);
                    editTextid.setLayoutParams(editTextParams);
                    editTextid.setBackgroundResource(R.color.white);

                    productboxesId.add(editTextid);
                    //editText.setId(i);
                    ll.addView(editTextid);
                    ml2.addView(ll);
                }

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getApplicationContext(), "button clicked====", Toast.LENGTH_LONG);

                        items = new String[productboxes.size()];
                        itemsId = new String[productboxesId.size()];

                        for (int i = 0; i < productboxes.size(); i++) {
                            items[i] = productboxes.get(i).getText().toString();
                            itemsId[i] = productboxesId.get(i).getText().toString();

                        }
                        Log.e("kajol========try==", items.length + "  " + itemsId);
                        productnamesstr = new StringBuilder();
                        productnamesIdstr = new StringBuilder();
                        for (int z = 0; z < items.length; z++) {
                            Log.e("kajol========try==", items[z] + "  " + itemsId[z]);

                          if(items[z]== items[items.length-1])
                            {
                                productnamesstr.append(items[z]);
                                productnamesIdstr.append(itemsId[z]);

                            }
                           else if(items[z].equals("")|| itemsId[z].equals(""))
                          {
                              productnamesstr.append(items[z]);
                              productnamesIdstr.append(itemsId[z]);

                          }
                            else{
                                productnamesstr.append(items[z] + ",");
                                productnamesIdstr.append(itemsId[z] + ",");
                            }

                        }
                        Log.e("kP========try==", productnamesstr.toString() + " " + productnamesIdstr.toString());

                        if (!productnamesIdstr.toString().isEmpty() && !name.isEmpty() && !email.isEmpty() && !productnamesIdstr.toString().isEmpty()) {
                            storeProducts(productnamesIdstr.toString(), name, email, productnamesstr.toString());
                        }
                        else
                        {

                            Toast.makeText(getApplicationContext(),"Please Enter Products",Toast.LENGTH_LONG).show();
                        }

                    }

                    private void storeProducts(String productnamesIdstr, String name, String email, String productnamesstr) {
                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                        Call<StoreProduct> responseCall = apiService.PRODUCT_STORE_CALL(productnamesIdstr, name, email, productnamesstr);
                        responseCall.enqueue(new Callback<StoreProduct>() {
                            @Override
                            public void onResponse(Call<StoreProduct> call, retrofit2.Response<StoreProduct> response) {

                                final StoreProduct response1 = response.body();
                                if (response.code() == 200) {
                                    if (!response1.isError()) {
                                        StoreProduct.ProductEntity product = response1.getProduct();
                                        ml2.removeAllViews();
                                        et.setText("");
                                        et.setHint("Enter no of products to be stored");

                                        Toast.makeText(getApplicationContext(),"Products Stored Successfully",Toast.LENGTH_LONG).show();
                                        Log.e("kajol===", "onResponse:=====success=== " + product.getName());
                                        Log.e("kajol===", "onResponse: " + product.getUniqueProductId());

                                    } else {
                                        Log.e("kajol===", "onResponse: " + response1.getErrorMsg());
                                    }
                                } else {
                                    Log.e("kajol===", "error code : " + response.code());
                                }
                            }

                            @Override
                            public void onFailure(Call<StoreProduct> call, Throwable t) {
                                //  hideDialog();
                                Log.e("kajol===", "onFailure: " + t.getMessage());
                            }


                        });
                    }
                });
            }

        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                 String newproductname;
                 String newproductid;

                newproductid=productidupdate.getText().toString();
                newproductname=newproduct.getText().toString();

                Log.e("kP===in====update=", newproductid+ " " + newproductname);

                if (!newproductname.isEmpty()&& !newproductid.isEmpty()) {
                    updateProducts(newproductname,newproductid);
                }
            }

            //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            private void updateProducts(String newproductname,String newproductid) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<UpdateProduct> responseCall = apiService.PRODUCT_UPDATE_CALL(newproductname,newproductid);
                responseCall.enqueue(new Callback<UpdateProduct>() {
                    @Override
                    public void onResponse(Call<UpdateProduct> call, retrofit2.Response<UpdateProduct> response) {

                        final UpdateProduct response1 = response.body();
                        if (response.code() == 200) {
                            if (!response1.isError()) {
                                // StoreProduct.ProductEntity product = response1.getProduct();
                                Toast.makeText(getApplicationContext(),"Products Updated Successfully",Toast.LENGTH_LONG).show();
                                Log.e("kajol===", "onResponse:=====successfully updated=== ");
                            } else {
                                Toast.makeText(getApplicationContext(),"Check Product Details again",Toast.LENGTH_LONG).show();
                                Log.e("kajol===", "onResponse: " + response1.getErrorMsg());
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Check Product Details again",Toast.LENGTH_LONG).show();
                            Log.e("kajol===", "error code : " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateProduct> call, Throwable t) {
                        //  hideDialog();
                        Toast.makeText(getApplicationContext(),"Check Product Details again",Toast.LENGTH_LONG).show();
                        Log.e("kajol===", "onFailure: " + t.getMessage());
                    }


                });
            }

        });


    }
    }

