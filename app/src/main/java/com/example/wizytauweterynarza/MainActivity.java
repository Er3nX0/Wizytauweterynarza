package com.example.wizytauweterynarza;

import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView wiekTextView;
    SeekBar wiekSeekBar;
    Spinner gatunekSpinner;
    Button zatwierdzButton;
    TextView wypelnioneDaneTextView;
    EditText imieINazwiskoEditText;
    EditText celWizytyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        wiekTextView = findViewById(R.id.wiekTextView);
        wiekSeekBar = findViewById(R.id.wiekSeekBar);
        gatunekSpinner = findViewById(R.id.gatunekSpinner);
        zatwierdzButton = findViewById(R.id.zatwierdzButton);
        wypelnioneDaneTextView = findViewById(R.id.wypelnioneDaneTextView);
        imieINazwiskoEditText = findViewById(R.id.imieINazwiskoEditText);
        celWizytyEditText = findViewById(R.id.celWizytyEditText);
        TimePicker godzinaWizytyTimePicker = findViewById(R.id.godzinaWizytyTimePicker);
        godzinaWizytyTimePicker.setIs24HourView(true);
        godzinaWizytyTimePicker.setHour(16);
        godzinaWizytyTimePicker.setMinute(0);



        wiekSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    wiekTextView.setText("" + progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.zwierzeta_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gatunekSpinner.setAdapter(adapter);

        gatunekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedUnit = parent.getItemAtPosition(position).toString();
                wiekTextView.setText("0");
                wiekSeekBar.setProgress(0);
                if(position == 0){
                    wiekSeekBar.setMax(18); //dla psa
                } else if (position == 1) {
                    wiekSeekBar.setMax(20); //dla kota
                } else if (position == 2){
                    wiekSeekBar.setMax(9);  //dla swinki morskiej
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                wiekTextView.setText("-");
            }
        });

        zatwierdzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wypelnioneDaneTextView.setVisibility(VISIBLE);
                wypelnioneDaneTextView.setText(
                        imieINazwiskoEditText.getText() + "," + gatunekSpinner.getSelectedItem().toString() +
                                "," + wiekSeekBar.getProgress() + "," + celWizytyEditText.getText() + "," +
                                godzinaWizytyTimePicker.getHour() + ":" + godzinaWizytyTimePicker.getMinute());
            }
        });

    }
}