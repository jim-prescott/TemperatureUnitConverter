package com.example.jimprescott.temperatureunitconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private double temperature;

    private EditText tempInput;
    private Spinner unitSelect;
    private Button convButton;

    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempInput = (EditText) findViewById(R.id.tempInput);

        unitSelect = (Spinner) findViewById(R.id.unitSelect);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.temp_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSelect.setAdapter(adapter);
        unitSelect.setOnItemSelectedListener(this);

        convButton = (Button) findViewById(R.id.convButton);
        convButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperature = Double.parseDouble(tempInput.getText().toString());
                View view = findViewById(R.id.relativeLayout);
                double convTemp;
                // C to F
                if(pos == 0) {
                    convTemp = (temperature * (9.0/5.0)) + 32.0;
                    convTemp = (double) Math.round(convTemp * 10) / 10;
                    showToast(temperature + " °C = " + convTemp + " °F.");
                }
                // F to C
                else if(pos == 1) {
                    convTemp = (temperature - 32.0) * (5.0/9.0);
                    convTemp = (double) Math.round(convTemp * 10) / 10;
                    showToast(temperature + " °F = " + convTemp + " °C.");
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

//    void showSnackbar(View view, String msg, int duration) {
//        Snackbar.make(view, msg, duration).show();
//    }
}
