package com.example.comp1786lecture3forminput;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private final String[] workStatusArray = {
            "Full Time",
            "Part Time",
            "Unemployment"
    };
    private Spinner spinnerWorkStatus;
    private CheckBox checkBoxAgreement;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String radioText="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerWorkStatus = (Spinner) findViewById(R.id.spinnerWorkStatus);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, workStatusArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWorkStatus.setAdapter((dataAdapter));

        radioGroup = (RadioGroup) findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                radioText = radioButton.getText().toString();
            }
        });

        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        Button saveButton = findViewById(R.id.buttonSave);


        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                checkBoxAgreement = findViewById(R.id.checkboxAgreement);

                // find the radiobutton by returned id

                if (!checkBoxAgreement.isChecked()){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "You must agree to the terms", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                getInputs();
            }
        });

    }

    public void showDatePickerDialog(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void updateDOB(LocalDate dob){
        TextView dobText = (TextView) findViewById(R.id.inputDob);
        dobText.setText(dob.toString());
    }

    private void getInputs(){
        EditText nameInput = findViewById(R.id.inputName);
        EditText emailInput = findViewById(R.id.inputEmail);
        EditText phoneInput = findViewById(R.id.inputPhone);
        TextView dobText = (TextView) findViewById(R.id.inputDob);
        spinnerWorkStatus = findViewById(R.id.spinnerWorkStatus);
        radioGroup = findViewById(R.id.radio);

        String strName = nameInput.getText().toString();
        String strPhone = phoneInput.getText().toString();
        String strEmail = emailInput.getText().toString();
        String strDob = dobText.getText().toString();
        String strWork = spinnerWorkStatus.getSelectedItem().toString();

        displayNextAlert(strName, strPhone, strEmail, radioText, strDob, strWork);
    }

    private void displayNextAlert(String strName, String strPhone, String strEmail, String strWork, String dobText, String radio){
        new AlertDialog.Builder(this).setTitle("Details entered").setMessage("Details entered: " +
                "\n" + strName +
                "\n" + strPhone +
                "\n" + strEmail +
                "\n" + strWork +
                "\n" + dobText +
                "\n" + radio

        )

        .setNeutralButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.itemNext:
                getInputs();
                return true;
            case R.id.itemExit:
                Toast.makeText(
                        getApplicationContext(),
                        "You asked to exit, but why not start another app?",
                        Toast.LENGTH_LONG
                ).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}