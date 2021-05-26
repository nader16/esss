package com.example.nader.e_commerceonlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ProdCategActivity extends AppCompatActivity {

    ArrayAdapter<String> EADB;
    ListView eList;
    ECommerceDB EComDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_categ);


        EComDB = new ECommerceDB(getApplicationContext());
        eList = (ListView) findViewById(R.id.prodcatlistview);
        EADB = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        eList.setAdapter(EADB);

        ((TextView) findViewById(R.id.textView2)).setText(getIntent().getExtras().getString("CatName"));

        if (getIntent().getExtras().getString("CatName").equals("Searching Result")) {  //search result
            EADB.add(getIntent().getExtras().getString("CatSearchResult"));
        } else {    //view all for categ
            Cursor curs = EComDB.fetchAProdbyCateg(getIntent().getExtras().getString("CatName"));
            //((TextView) findViewById(R.id.textView2)).setText( getIntent().getExtras().getString("CustName"));
            while (!curs.isAfterLast()) {
                EADB.add(curs.getString(0) + "\nQuantity: "
                        + curs.getString(2) + "\nPrice: "
                        + curs.getString(1) + "$");
                curs.moveToNext();
            }
        }

        Button IncBtn = (Button) findViewById(R.id.incquan_btn);
        IncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(((TextView) findViewById(R.id.productorder_txtview)).getText().toString().equals("Choose a product to order"))) {
                    TextView QuanTxtView = (TextView) findViewById(R.id.quantity_txtview);
                    String QuanTxtView_str = QuanTxtView.getText().toString();
                    int x = Integer.parseInt(QuanTxtView_str);
                    int y = Integer.parseInt(EComDB.fetchAProdbyName(((TextView) findViewById(R.id.productorder_txtview)).getText().toString())
                            .getString(2));
                    if (x < y)
                        QuanTxtView.setText(String.valueOf(x + 1));
                }
            }
        });

        Button DecBtn = (Button) findViewById(R.id.decquan_btn);
        DecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(((TextView) findViewById(R.id.productorder_txtview)).getText().toString().equals("Choose a product to order"))) {
                    TextView QuanTxtView = (TextView) findViewById(R.id.quantity_txtview);
                    String QuanTxtView_str = QuanTxtView.getText().toString();
                    int x = Integer.parseInt(QuanTxtView_str);
                    if (x > 0)
                        QuanTxtView.setText(String.valueOf(x - 1));
                }
            }
        });

        eList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedprod = ((TextView) view).getText().toString();
                String[] parts = selectedprod.split("\n");
                ((TextView) findViewById(R.id.productorder_txtview)).setText(parts[0]);
                ((TextView) findViewById(R.id.quantity_txtview)).setText("0");

            }
        });

        Button addorder = (Button) findViewById(R.id.addordertocart_btn);
        addorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((((TextView) findViewById(R.id.productorder_txtview)).getText().toString().equals("Choose a product to order"))
                        || ((TextView) findViewById(R.id.quantity_txtview)).getText().toString().equals("0")
                        || (((EditText) findViewById(R.id.editText3)).getText().toString()).equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill required data please", Toast.LENGTH_LONG).show();
                } else {
                    String product_name = ((TextView) findViewById(R.id.productorder_txtview)).getText().toString();
                    String product_id = EComDB.fetchAProdbyName(product_name).getString(4);

                    TextView QuanTxtView = (TextView) findViewById(R.id.quantity_txtview);
                    String QuanTxtView_str = QuanTxtView.getText().toString();

                    String order_address = ((EditText) findViewById(R.id.editText3)).getText().toString();
                    String customer_id = getIntent().getExtras().getString("CustID");
                    String currdate = Calendar.getInstance().getTime().toString();

                    EComDB.AddAnOrder(currdate, customer_id, order_address);

                    EComDB.AddAnOrderDetails(currdate, product_id, QuanTxtView_str);
                    Toast.makeText(getApplicationContext(), "Product is added in your cart", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

        Button getlocation = (Button) findViewById(R.id.getlocation_btn);

        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
