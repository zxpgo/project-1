package com.example.dztx;

import android.content.res.Resources;
import android.os.AsyncTask;
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

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Spinner m1KindsSpinner;
    private Spinner m2KindsSpinner;
    private Spinner m3KindsSpinner;


    private int index;
    ArrayAdapter<String> kind1Adapter, kind2Adapter, kind3Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataAsyncTask task = new DataAsyncTask();
        task.execute("http://111.230.111.81/data/cpu/");

        m1KindsSpinner = (Spinner) findViewById(R.id.spinner_kind1);
        m2KindsSpinner = (Spinner) findViewById(R.id.spinner_kind2);
        m3KindsSpinner = (Spinner) findViewById(R.id.spinner_kind3);

        setupSpinner();
    }

    private class DataAsyncTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls){
            Log.e("result","CPU ---------------------------------");
            String result = QueryUtils.fetchData(urls[0]);
            Log.e("result","CPU " + result);
            return result;
        }

        @Override
        protected void onPostExecute(String data) {
            // 清除之前地震数据的适配器

            }
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        /** ArrayAdapter kind1SpinnerAdapter = ArrayAdapter.createFromResource(this,
         R.array.array_kind1_options, android.R.layout.simple_spinner_item);
         final ArrayAdapter kind2SpinnerAdapter = ArrayAdapter.createFromResource(this,
         R.array.array_kind1_options, android.R.layout.simple_spinner_item);
         ArrayAdapter kind3SpinnerAdapter = ArrayAdapter.createFromResource(this,
         R.array.array_kind3_options, android.R.layout.simple_spinner_item);

         // Specify dropdown layout style - simple list view with 1 item per line
         kind1SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
         kind2SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
         kind3SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

         // Apply the adapter to the spinner
         m1KindsSpinner.setAdapter(kind1SpinnerAdapter);
         m2KindsSpinner.setAdapter(kind2SpinnerAdapter);
         m3KindsSpinner.setAdapter(kind3SpinnerAdapter);*/

        Resources res = getResources();
        String[] kind1 = res.getStringArray(R.array.array_kind1_options);

        kind1Adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, kind1);
        String[] array = res.getStringArray(R.array.array_kind2_options);
        final String[][] kind2 = getTwoDimensionalArray(array);
        kind2Adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, kind2[0]);
        String[] array1 = res.getStringArray(R.array.array_kind3_options);
        final String[][] kind3 = getTwoDimensionalArray(array1);
        kind3Adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, kind3[0]);

        m1KindsSpinner.setAdapter(kind1Adapter);

        m2KindsSpinner.setAdapter(kind2Adapter);
        m3KindsSpinner.setAdapter(kind3Adapter);

        // Set the integer mSelected to the constant values
        m1KindsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                kind2Adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, kind2[position]);
                m2KindsSpinner.setAdapter(kind2Adapter);
            }


            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // mGender = 0; // Unknown
            }
        });
        m2KindsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = index * 3 + position;
                kind3Adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, kind3[pos]);
                m3KindsSpinner.setAdapter(kind3Adapter);
            }


            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // mGender = 0; // Unknown
            }
        });
    }

    /**
     * 按设定规则解析一维数组为二维数组
     *
     * @param array
     * @return
     */
    private String[][] getTwoDimensionalArray(String[] array) {
        String[][] twoDimensionalArray = null;
        for (int i = 0; i < array.length; i++) {
            String[] tempArray = array[i].split(",");
            if (twoDimensionalArray == null) {
                twoDimensionalArray = new String[array.length][tempArray.length];
            }
            for (int j = 0; j < tempArray.length; j++) {
                twoDimensionalArray[i][j] = tempArray[j];
            }
        }
        return twoDimensionalArray;
    }

}




