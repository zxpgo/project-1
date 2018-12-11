package com.example.dztx;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.dztx.DrawLineChart;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Spinner m1KindsSpinner;
    private Spinner m2KindsSpinner;
    private Spinner m3KindsSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m1KindsSpinner = (Spinner) findViewById(R.id.spinner_kind1);
        m2KindsSpinner = (Spinner) findViewById(R.id.spinner_kind2);
        m3KindsSpinner = (Spinner) findViewById(R.id.spinner_kind3);

        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter kind1SpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_kind1_options, android.R.layout.simple_spinner_item);
        ArrayAdapter kind2SpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_kind2_options, android.R.layout.simple_spinner_item);
        ArrayAdapter kind3SpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_kind3_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        kind1SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        kind2SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        kind3SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        m1KindsSpinner.setAdapter(kind1SpinnerAdapter);
        m2KindsSpinner.setAdapter(kind2SpinnerAdapter);
        m3KindsSpinner.setAdapter(kind3SpinnerAdapter);

        // Set the integer mSelected to the constant values
        m2KindsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.cpu))) {
                       //cpu
                    } else if (selection.equals(getString(R.string.xk))) {
                        //显卡
                    } else {
                        //固态
                    }
                }
            }


                    // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

               // mGender = 0; // Unknown
            }
        });
    }
}




