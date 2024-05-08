package com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz02.R;

public class MainAktivity extends AppCompatActivity {

    private RadioGroup radioGroupType, radioGroupExtras;
    private EditText editHours;
    private Button buttonOrder;
    private TextView textTotal;

    private String[] types = {"PS5", "PS4", "PS3", "PSVR"};
    private String[] extras = {"Indomie", "Mie Ayam", "Somay"};

    // Harga per jam untuk setiap tipe PS
    private int[] pricesPerHour = {10000, 8000, 5000, 20000};

    // Harga tambahan
    private int[] extraPrices = {7000, 10000, 5000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.quiz02.R.layout.activity_main);

        radioGroupType = findViewById(R.id.radioGroupType);
        radioGroupExtras = findViewById(R.id.radioGroupExtras);
        editHours = findViewById(R.id.editHours);
        buttonOrder = findViewById(R.id.buttonOrder);
        textTotal = findViewById(R.id.textTotal);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        int selectedTypeId = radioGroupType.getCheckedRadioButtonId();
        int selectedExtraId = radioGroupExtras.getCheckedRadioButtonId();
        String hoursStr = editHours.getText().toString();

        if (!hoursStr.isEmpty() && selectedTypeId != -1 && selectedExtraId != -1) {
            RadioButton selectedType = findViewById(selectedTypeId);
            RadioButton selectedExtra = findViewById(selectedExtraId);

            int selectedTypeIndex = radioGroupType.indexOfChild(selectedType);
            int selectedExtraIndex = radioGroupExtras.indexOfChild(selectedExtra);

            int hours = Integer.parseInt(hoursStr);
            int typePrice = pricesPerHour[selectedTypeIndex];
            int extraPrice = extraPrices[selectedExtraIndex];
            int total = (typePrice + extraPrice) * hours;

            Intent intent = new Intent(MainAktivity.this, DitailAktivity.class);
            intent.putExtra("type", selectedType.getText().toString());
            intent.putExtra("extra", selectedExtra.getText().toString());
            intent.putExtra("hours", hours);
            intent.putExtra("total", total);
            startActivity(intent);

            textTotal.setText("Total: Rp " + total);
        } else {
            Toast.makeText(MainAktivity.this, "Pilih tipe, tambahan, dan masukkan jumlah jam.", Toast.LENGTH_SHORT).show();
        }
    }
}