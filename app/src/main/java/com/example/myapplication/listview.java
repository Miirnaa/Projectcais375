package com.example.myapplication;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class listview extends AppCompatActivity {

    String[] mobiles;
    ListView listView;
RequestOptions option;
    Intent intent;
    private final String JSON_URL = "https://www.palpharmacy.com/getPharmacies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List of mobiles");
        getJSON("https://www.palpharmacy.com/mobiles.php");

        listView = (ListView) findViewById(R.id.listView);
        intent = new Intent(listview.this, details.class);
        jsonrequest();

    }


    private void getJSON(final String urladdress) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {

                    add_to(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("https://www.palpharmacy.com/mobiles.php");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json);
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSONObj = new GetJSON();
        getJSONObj.execute();
    }

    private void add_to(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        mobiles = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            mobiles[i] = obj.getString("name");


        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mobiles) {

            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1)
                    view.setBackgroundColor(Color.rgb(14, 116, 197));
                else
                    view.setBackgroundColor(Color.WHITE);
                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("POSITION", position);
                startActivity(intent);
            }
        });
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
//    public void description(){
//        String name = getIntent().getExtras().getString("name");
//        String price = getIntent().getExtras().getString("price");
//        String storage = getIntent().getExtras().getString("storage");
//        String image_url = getIntent().getExtras().getString("mobileurl");
//        String description = getIntent().getExtras().getString("spec");
//
//        TextView tv_name = findViewById(R.id.name_result);
//        TextView tv_price = findViewById(R.id.price_db);
//        TextView tv_storage = findViewById(R.id.store_db);
//        TextView tv_descr = findViewById(R.id.description_db);
//        ImageView img = findViewById(R.id.imageView);
//
//        tv_name.setText(name);
//        tv_price.setText(price);
//        tv_storage.setText(storage);
//        tv_descr.setText(description);
//    }
}





