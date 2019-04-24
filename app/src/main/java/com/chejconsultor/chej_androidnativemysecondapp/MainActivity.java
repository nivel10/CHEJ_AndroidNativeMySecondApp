package com.chejconsultor.chej_androidnativemysecondapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //region Attributes

    private TextView lblStudentStatus;
    private EditText txtMaths;
    private EditText txtPhysical;
    private EditText txtChemistry;

    private String statusStudent;
    private String maths;
    private String physical;
    private String chemistry;

    private double mathsValue;
    private double physicalValue;
    private double chemistryValue;

    //endregion Attributes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InitalData();
    }

    //region Methods

    private void InitalData() {

        this.lblStudentStatus = (TextView)findViewById(R.id.lblStudentStatus);
        this.lblStudentStatus.setText("Student status...!!!");
        this.txtMaths = (EditText)findViewById(R.id.txtMaths);
        this.txtPhysical = (EditText)findViewById(R.id.txtPhysical);
        this.txtChemistry = (EditText)findViewById(R.id.txtChemistry);
    }

    public void GetStudentStatus(
            View _view)
    {
        this.GetDataControls(1);
        this.IsApproved();
    }

    public void SetClearControls(
            View _view)
    {
        this.GetDataControls(0);
    }

    private void GetDataControls(
            int _processType)
    {
        this.statusStudent = this.lblStudentStatus.getText().toString().trim();
        this.maths = this.txtMaths.getText().toString().trim();
        this.physical = this.txtPhysical.getText().toString().trim();
        this.chemistry = this.txtChemistry.getText().toString().trim();

        switch (_processType)
        {
            case 0:
                this.lblStudentStatus.setTextColor(Color.GRAY);
                this.lblStudentStatus.setText("Student status...!!!");
                this.txtMaths.setText("");
                this.txtPhysical.setText("");
                this.txtChemistry.setText("");
                break;

            case 1:
                this.mathsValue = Double.parseDouble(this.maths.length() == 0 ? "0.00" : this.maths);
                this.physicalValue = Double.parseDouble(this.physical.length() == 0 ? "0.00" : this.physical);
                this.chemistryValue = Double.parseDouble(this.chemistry.length() == 0 ? "0.00" : this.chemistry);
                break;
        }

    }

    private void IsApproved()
    {
        double average = 0.00;
        average = (this.mathsValue + this.physicalValue + this.chemistryValue) / 3.00;

        if(average >= 6) {
            this.lblStudentStatus.setText("Status approved with: " + this.SetDecimalFormat(
                    average,
                    2));
        }
        else {
            this.lblStudentStatus.setText("Status reprobate with: " + this.SetDecimalFormat(
                    average,
                    2));
        }

        this.lblStudentStatus.setTextColor(average >= 6 ? Color.GREEN : Color.RED);
    }

    private String SetDecimalFormat(
            double _value,
            int _decimals)
    {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(_decimals);
        return decimalFormat.format(_value);
    }

    //endregion Methods
}