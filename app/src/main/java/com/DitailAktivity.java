package com;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz02.R;

public class DitailAktivity extends AppCompatActivity {

    private TextView textType, textExtra, textHours, textTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditail);

        textType = findViewById(R.id.textType);
        textExtra = findViewById(R.id.textExtra);
        textHours = findViewById(R.id.textHours);
        textTotal = findViewById(R.id.textTotal);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String type = extras.getString("type");
            String extra = extras.getString("extra");
            int hours = extras.getInt("hours");
            int total = extras.getInt("total");

            textType.setText("Type: " + type);
            textExtra.setText("Extra: " + extra);
            textHours.setText("Waktu: " + hours + " jam");
            textTotal.setText("Total: Rp " + total);
        }
    }
}


