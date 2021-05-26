package com.example.nader.e_commerceonlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AfterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);


        ((TextView) findViewById(R.id.textView5)).setText("Welcome, "+ getIntent().getExtras().getString("CustName"));

        Button viewcategbtn = (Button) findViewById(R.id.gotoviewcateg_btn);
        viewcategbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AfterLogin.this, CategoryActivity.class);
                i.putExtra("CustID", getIntent().getExtras().getString("CustID"));
                i.putExtra("CustName", getIntent().getExtras().getString("CustName"));
                i.putExtra("CustUsername", getIntent().getExtras().getString("CustUsername"));
                startActivity(i);
            }
        });

        Button searchbtn = (Button) findViewById(R.id.gotosearchaprod_btn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AfterLogin.this, SearchActivity.class);
                i.putExtra("QRhelper", "noQRcode");
                i.putExtra("CustID", getIntent().getExtras().getString("CustID"));
                i.putExtra("CustName", getIntent().getExtras().getString("CustName"));
                i.putExtra("CustUsername", getIntent().getExtras().getString("CustUsername"));
                startActivity(i);
            }
        });

        Button qrscanbtn = (Button) findViewById(R.id.gotoscanqr_btn);

        qrscanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AfterLogin.this, scanQR.class);
                i.putExtra("CustID", getIntent().getExtras().getString("CustID"));
                i.putExtra("CustName", getIntent().getExtras().getString("CustName"));
                i.putExtra("CustUsername", getIntent().getExtras().getString("CustUsername"));
                startActivity(i);
            }
        });


        Button viewmycart = (Button) findViewById(R.id.gotomycart_btn);

        viewmycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AfterLogin.this, ShoppingCart.class);
                i.putExtra("CustID", getIntent().getExtras().getString("CustID"));
                i.putExtra("CustName", getIntent().getExtras().getString("CustName"));
                i.putExtra("CustUsername", getIntent().getExtras().getString("CustUsername"));
                startActivity(i);
            }
        });


    }
}
