package com.example.nader.e_commerceonlineshopping;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    private final int REQ_CODE_SPEECH_OUTPUT = 143;
    ArrayAdapter<String> EADB;
    ListView eList;
    ECommerceDB EComDB;
    private Button openMic;
    private EditText showVoiceText;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_OUTPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> VoiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    showVoiceText.setText(VoiceInText.get(0));
                }
                break;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        openMic = (Button) findViewById(R.id.voice_button);
        showVoiceText = (EditText) findViewById(R.id.editText);
        openMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hiiii Speak Now..");
                try {
                    startActivityForResult(i, REQ_CODE_SPEECH_OUTPUT);
                } catch (ActivityNotFoundException tim) {
                    Toast.makeText(getApplicationContext(), "Google mic is not opened", Toast.LENGTH_LONG).show();
                }
            }
        });


        EComDB = new ECommerceDB(getApplicationContext());
        eList = (ListView) findViewById(R.id.searchlistview);
        EADB = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        eList.setAdapter(EADB);


        EditText search_txt = (EditText) findViewById(R.id.editText);
        if (!getIntent().getExtras().getString("QRhelper").equals("noQRcode")) {
            search_txt.setText(getIntent().getExtras().getString("QRhelper"));
            Cursor curs = EComDB.fetchAProdbyName("%" +
                    getIntent().getExtras().getString("QRhelper") + "%");
            while (!curs.isAfterLast()) {
                EADB.add(curs.getString(0) + "\nQuantity: "
                        + curs.getString(2) + "\nPrice: "
                        + curs.getString(1) + "$");
                curs.moveToNext();
            }
        } else {
            Cursor curs = EComDB.fetchAProdbyName("%");
            while (!curs.isAfterLast()) {
                EADB.add(curs.getString(0) + "\nQuantity: "
                        + curs.getString(2) + "\nPrice: "
                        + curs.getString(1) + "$");
                curs.moveToNext();
            }
        }


        search_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                EADB.clear();
                Cursor curs = EComDB.fetchAProdbyName("%" + s.toString() + "%");
                while (!curs.isAfterLast()) {
                    EADB.add(curs.getString(0) + "\nQuantity: "
                            + curs.getString(2) + "\nPrice: "
                            + curs.getString(1) + "$");
                    curs.moveToNext();

                }

            }
        });

        eList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SearchActivity.this, ProdCategActivity.class);
                i.putExtra("CatName", "Searching Result");
                i.putExtra("CatSearchResult", ((TextView) view).getText().toString());

                i.putExtra("CustID", getIntent().getExtras().getString("CustID"));
                i.putExtra("CustName", getIntent().getExtras().getString("CustName"));
                i.putExtra("CustUsername", getIntent().getExtras().getString("CustUsername"));
                startActivity(i);

            }
        });


    }
}
