package com.example.nader.e_commerceonlineshopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final ECommerceDB EComDB = new ECommerceDB(getApplicationContext());
        final String[] datee = {""};

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                datee[0] = dayOfMonth + "/" + ((int) (month + 1)) + "/" + year;
            }
        });


        Button signup = (Button) findViewById(R.id.signup2_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customername = ((EditText) findViewById(R.id.custnamesignup_txtbox)).getText().toString();
                String username = ((EditText) findViewById(R.id.usernamesignup_txtbox)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordsignup_txtbox)).getText().toString();
                String job = ((EditText) findViewById(R.id.jobsignup_txtbox)).getText().toString();
                RadioButton rd1 = (RadioButton) findViewById(R.id.malesignup_rdbtn);
                RadioButton rd2 = (RadioButton) findViewById(R.id.femalesignup_rdbtn);


                if (datee[0].equals("") || job.equals("") || password.equals("") || username.equals("") || customername.equals("") || (!rd1.isChecked() && !rd2.isChecked())) {
                    Toast.makeText(getApplicationContext(), "answer all fields", Toast.LENGTH_LONG).show();
                } else if (EComDB.ExistCustUserName(username)) {
                    Toast.makeText(getApplicationContext(), "Invalid Username ", Toast.LENGTH_LONG).show();
                } else {
                    EComDB.addCust(customername, username, password, job, rd1.isChecked() ? "M" : "F", datee[0]);
                    Toast.makeText(getApplicationContext(), "Signed UP successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}
