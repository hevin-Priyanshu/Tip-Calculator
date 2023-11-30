package com.demo.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import com.demo.tipcalculator.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.calculateButton.setOnClickListener(view -> {
            if (binding.costOfService.getEditText().getText().toString().isEmpty()) {
                Toast.makeText(this, "Please Enter Value!", Toast.LENGTH_SHORT).show();
            } else {
                calculateTip();
            }
        });
    }

    private void calculateTip() {
        String stringInTextField = binding.costOfService.getEditText().getText().toString();
        double cost = Double.parseDouble(stringInTextField);

        double tipPercentage = getTipPercentage();

        double tip = tipPercentage * cost;

        if (binding.roundUpSwitch.isChecked()) {
            tip = Math.ceil(tip);
        }

        String formattedTip = NumberFormat.getCurrencyInstance().format(tip);
        binding.tipResult.setText(getString(R.string.tip_amount, formattedTip));

//        binding.costOfService.getEditText().setText("");
    }

    private double getTipPercentage() {
        int checkedRadioButtonId = binding.tipOptions.getCheckedRadioButtonId();

        if (checkedRadioButtonId == R.id.option_twenty_percent) {
            return 0.15;
        } else if (checkedRadioButtonId == R.id.option_eighteen_percent) {
            return 0.12;
        } else {
            return 0.08;
        }
    }
}
