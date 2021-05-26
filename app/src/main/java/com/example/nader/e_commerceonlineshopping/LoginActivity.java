package com.example.nader.e_commerceonlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    ArrayAdapter<String> EADB;
    ListView eList;
    ECommerceDB EComDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EComDB = new ECommerceDB(getApplicationContext());
        eList = (ListView) findViewById(R.id.customerslistview);
        EADB = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        eList.setAdapter(EADB);
        Cursor curs = EComDB.fetchAllRememberMe();
        while (!curs.isAfterLast()) {
            String currusername = curs.getString(0),
                    gendeer = ((EComDB.fetchACustbyUserName(currusername).getString(3).equals("F")) ? "Female" : "Male");
            EADB.add(currusername + "\n" +
                    "Name: " + EComDB.fetchACustbyUserName(currusername).getString(0) + "\n" +
                    "Job: " + EComDB.fetchACustbyUserName(currusername).getString(5) + "\n" +
                    "Gender: " + gendeer);
            curs.moveToNext();
        }

        Button forgetpw = (Button) findViewById(R.id.forgetpw_btn);
        forgetpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgetPassword.class);
                startActivity(i);
            }
        });


        Button login = (Button) findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.usernamelogin_txtbox)).getText().toString();
                String passwoord = ((EditText) findViewById(R.id.passwordlogin_txtbox)).getText().toString();
                if (EComDB.ExistCustUsernameandPw(username, passwoord)) {
                    if (((CheckBox) findViewById(R.id.rememberme_checkBox)).isChecked()) {
                        EComDB.InsertInRememberMe(username);
                    }
                    Toast.makeText(getApplicationContext(), "Logined IN successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, AfterLogin.class);
                    i.putExtra("CustID", EComDB.fetchACustbyUserName(username).getString(6));
                    i.putExtra("CustName", EComDB.fetchACustbyUserName(username).getString(0));
                    i.putExtra("CustUsername", username);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalied Username or Password", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button signup = (Button) findViewById(R.id.signup1_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        eList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedprod = ((TextView) view).getText().toString();
                String[] parts = selectedprod.split("\n");
                ((EditText) findViewById(R.id.usernamelogin_txtbox)).setText(parts[0]);
                ((EditText) findViewById(R.id.passwordlogin_txtbox))
                        .setText(EComDB.fetchACustbyUserName(parts[0]).getString(2));
            }
        });
    }

    @Override
    protected void onRestart() {
        ((EditText) findViewById(R.id.usernamelogin_txtbox)).setText("");
        ((EditText) findViewById(R.id.passwordlogin_txtbox)).setText("");
        ((CheckBox) findViewById(R.id.rememberme_checkBox)).setChecked(false);
        EADB.clear();
        eList = (ListView) findViewById(R.id.customerslistview);
        EADB = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        eList.setAdapter(EADB);

        Cursor curs = EComDB.fetchAllRememberMe();
        while (!curs.isAfterLast()) {
            String currusername = curs.getString(0),
                    gendeer = ((EComDB.fetchACustbyUserName(currusername).getString(3).equals("F")) ? "Female" : "Male");
            EADB.add(currusername + "\n" +
                    "Name: " + EComDB.fetchACustbyUserName(currusername).getString(0) + "\n" +
                    "Job: " + EComDB.fetchACustbyUserName(currusername).getString(5) + "\n" +
                    "Gender: " + gendeer);
            curs.moveToNext();
        }

        super.onRestart();
    }
}
