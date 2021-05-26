package com.example.nader.e_commerceonlineshopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    ECommerceDB EComDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        EComDB = new ECommerceDB(getApplicationContext());
        final String[] datee = {""};

        CalendarView calendarView = findViewById(R.id.calendarView2);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                datee[0] = dayOfMonth + "/" + ((int) (month + 1)) + "/" + year;
            }
        });


        Button updatepw = (Button) findViewById(R.id.Updatebtn_forg);
        updatepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UN = ((EditText) findViewById(R.id.username_txtbox_forg)).getText().toString();
                String NPW = ((EditText) findViewById(R.id.newpassword_txtbox_forg)).getText().toString();

                if (EComDB.UpdateCustPassword(UN, datee[0], NPW)) {
                    Toast.makeText(getApplicationContext(), "Password updated successfully", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid data/fields", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
