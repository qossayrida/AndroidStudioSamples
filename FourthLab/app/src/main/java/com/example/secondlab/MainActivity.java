package com.example.secondlab;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        Button addCustomerButton = new Button(this);
        addCustomerButton.setText("Add Customer");
        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCustomerActivity.class);
                startActivity(intent);
            }
        });

        mainLayout.addView(addCustomerButton);

        LinearLayout customerListLayout = new LinearLayout(this);
        customerListLayout.setOrientation(LinearLayout.VERTICAL);

        for (Customer customer : Customer.customersArrayList) {
            TextView customerTextView = new TextView(this);
            customerTextView.setText(customer.toString());
            customerListLayout.addView(customerTextView);
        }

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(customerListLayout);
        mainLayout.addView(scrollView);

        setContentView(mainLayout);
    }
}