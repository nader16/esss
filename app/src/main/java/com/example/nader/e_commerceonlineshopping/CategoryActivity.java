package com.example.nader.e_commerceonlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    ArrayAdapter<String> EADB;
    ListView eList;
    ECommerceDB EComDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        EComDB = new ECommerceDB(getApplicationContext());
        eList = (ListView) findViewById(R.id.categorylistview);
        EADB = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        eList.setAdapter(EADB);
        Cursor curs = EComDB.fetchAllCateg();
        while (!curs.isAfterLast()) {
            EADB.add(curs.getString(0));
            curs.moveToNext();
        }
        eList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CategoryActivity.this, ProdCategActivity.class);
                i.putExtra("CatName", ((TextView) view).getText().toString());
                i.putExtra("CustID", getIntent().getExtras().getString("CustID"));
                i.putExtra("CustName", getIntent().getExtras().getString("CustName"));
                i.putExtra("CustUsername", getIntent().getExtras().getString("CustUsername"));
                startActivity(i);
            }
        });
    }
}
