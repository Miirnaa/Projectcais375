package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    RadioGroup rgPhones;
    Button btnSubmit;
    private final String JSON_URL = "https://www.palpharmacy.com/getPharmacies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        rgPhones = findViewById(R.id.rgPhones);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedId = rgPhones.getCheckedRadioButtonId();
                if (checkedId == -1) {
                    message.message(getApplicationContext(), "please select One of the following");
                } else {
                    findRadioButtons(checkedId);
                }
            }
        });
    }

    private void findRadioButtons(int checkedId) {
        switch (checkedId) {

            case R.id.view:

                intent = new Intent(MainActivity.this, listview.class);
                startActivity(intent);
                break;

            case R.id.add:

                Intent intent = new Intent(MainActivity.this, Insert.class);
                startActivity(intent);
                break;

            case R.id.update:
                intent = new Intent(MainActivity.this, update_list.class);
                startActivity(intent);
                break;

            case R.id.delete:
                intent = new Intent(MainActivity.this, listview.class);
                // the code which delete the record doesn't work and because of no time i put the listview class but you
                // can see the full code on the class called "delete"
                startActivity(intent);
                break;

        }
    }

}
