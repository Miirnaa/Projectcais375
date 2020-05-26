package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class details extends AppCompatActivity {
    RequestOptions option;
    private final String JSON_URL = "https://www.palpharmacy.com/getPharmacies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Details");

        String name = getIntent().getExtras().getString("name");
        String price = getIntent().getExtras().getString("price");
        String storage = getIntent().getExtras().getString("storage");
        String image_url = getIntent().getExtras().getString("mobileurl");
        String description = getIntent().getExtras().getString("spec");

        TextView tv_name = findViewById(R.id.name_result);
        TextView tv_price = findViewById(R.id.price_db);
        TextView tv_storage = findViewById(R.id.store_db);
        TextView tv_descr = findViewById(R.id.description_db);
        ImageView img = findViewById(R.id.imageView);

        tv_name.setText(name);
        tv_price.setText(price);
        tv_storage.setText(storage);
  tv_descr.setText(description);
//        option = new RequestOptions().centerCrop().placeholder(R.drawable.mobile).error(R.drawable.mobile);

//        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.mobile).error(R.drawable.mobile);
//        Glide.with(this).load(image_url).apply(requestOptions).into(img);
    }

 private void jsonrequest() {
        JsonArrayRequest request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
         public void onResponse(JSONArray response) {

                JSONObject jsonObject;

                for (int i = 0; i < response.length(); i++) {

                   try {
                    jsonObject = response.getJSONObject(i);
                        Mob mobiles = new Mob();
                        mobiles.setName(jsonObject.getString("name"));
                        mobiles.setStorage(jsonObject.getString("storage"));
                      mobiles.setSpecs(jsonObject.getString("specs"));
                        mobiles.setMobileurl(jsonObject.getString("mobileurl"));
                   } catch (JSONException e) {
                       e.printStackTrace(); }


                }
            }
        }, new Response.ErrorListener() {
          @Override
            public void onErrorResponse(VolleyError error) {

           }
     });
    }
    public void openactivity() {
      Intent intent = new Intent(this, details.class);
        intent.putExtra("mobileurl", getIntent().getExtras().getString("mobileurl"));
        intent.putExtra("name", getIntent().getExtras().getString("name"));

        startActivity(intent);

    }
    }

