package com.example.nader.e_commerceonlineshopping;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends AppCompatActivity {


    ArrayAdapter<String> EADB;
    ListView eList;
    ECommerceDB EComDB;
    List<String> ProductNames;
    List<String> ProductQuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        final String customer_id = getIntent().getExtras().getString("CustID");
        String customer_name = getIntent().getExtras().getString("CustName");
        String customer_username = getIntent().getExtras().getString("CustUsername");

        ((TextView) findViewById(R.id.textView4)).setText(customer_name + ", " +
                ((TextView) findViewById(R.id.textView4)).getText().toString());

        EComDB = new ECommerceDB(getApplicationContext());
        ProductNames = new ArrayList<>();
        ProductQuan = new ArrayList<>();
        eList = (ListView) findViewById(R.id.cart_listview);
        EADB = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        eList.setAdapter(EADB);
        Cursor curs = EComDB.fetchCartbyUserName(customer_id);
        //  if (curs.getCount() >= 1)
        while (!curs.isAfterLast()) {

            ProductNames.add(curs.getString(0));
            ProductQuan.add(curs.getString(2));

            EADB.add(curs.getString(0) + "\n" +
                    curs.getString(3) + "\n" +
                    curs.getString(1) + "$\nQuantity: " +
                    curs.getString(2) + "\nOrder Address: " +
                    curs.getString(4));
            curs.moveToNext();
        }
        eList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedorder = ((TextView) view).getText().toString();
                String[] parts = selectedorder.split("\n");
                String prod_name = parts[0], order_date = parts[1];

                ProductQuan.remove(ProductNames.indexOf(prod_name));
                ProductNames.remove(prod_name);

                EComDB.DeleteFromCart(order_date, prod_name);
                EADB.remove(selectedorder);
                Toast.makeText(getApplicationContext(), "Order had been removed from your cart", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        Button submitorders = (Button) findViewById(R.id.submitorders_btn);
        submitorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EADB.getCount() == 0)
                    Toast.makeText(getApplicationContext(), "No orders in your cart to submit", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(getApplicationContext(), "Orders submited successfully", Toast.LENGTH_LONG).show();
                    EADB.clear();
                    EComDB.CompeteAllOrdersForCustomer(customer_id, ProductNames, ProductQuan);
                }

            }
        });


    }
}
