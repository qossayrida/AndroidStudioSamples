package com.example.secondlab;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    LinearLayout customerListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
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

        Button removeAllCustomersButton = new Button(this);
        removeAllCustomersButton.setText("Remove All Customers");
        removeAllCustomersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this, "DB_NAME_EXP4", null, 1);
                dataBaseHelper.deleteAllCustomers();
                onResume(); // Refresh the customer list
            }
        });

        mainLayout.addView(removeAllCustomersButton);

        customerListLayout = new LinearLayout(this);
        customerListLayout.setOrientation(LinearLayout.VERTICAL);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(customerListLayout);
        mainLayout.addView(scrollView);
        setContentView(mainLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this, "DB_NAME_EXP4", null, 1);
        customerListLayout.removeAllViews();

        Cursor customersCursor = dataBaseHelper.getLastCustomerNameStartWithB();

        if (customersCursor.moveToLast()){
            addCustomerStartWithB(customersCursor.getString(1), customersCursor.getString(2));} else {
            TextView textView = new TextView(MainActivity.this);
            textView.setText("No customers Start with B\n\n");
            customerListLayout.addView(textView);
        }

        Cursor allCustomersCursor = dataBaseHelper.getAllCustomers();
        if (allCustomersCursor.moveToFirst()) {
            do {
                addCustomerView(allCustomersCursor.getString(0), allCustomersCursor.getString(1), allCustomersCursor.getString(2), allCustomersCursor.getString(3));
            } while (allCustomersCursor.moveToNext());
        } else {
            // No customers found, handle the empty state if necessary
            TextView textView = new TextView(MainActivity.this);
            textView.setText("No customers found.");
            customerListLayout.addView(textView);
        }
        allCustomersCursor.close(); // Close the cursor to release resources
    }

    private void addCustomerView(String id, String name, String phone, String gender) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View customerView = inflater.inflate(R.layout.customer_item, customerListLayout, false);

        TextView customerId = customerView.findViewById(R.id.customerId);
        TextView customerName = customerView.findViewById(R.id.customerName);
        TextView customerPhone = customerView.findViewById(R.id.customerPhone);
        TextView customerGender = customerView.findViewById(R.id.customerGender);

        customerId.setText("Id: " + id);
        customerName.setText("Name: " + name);
        customerPhone.setText("Phone: " + phone);
        customerGender.setText("Gender: " + gender);

        customerListLayout.addView(customerView);
    }

    private void addCustomerStartWithB(String name, String phone) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View customerView = inflater.inflate(R.layout.customer_start_with_b, customerListLayout, false);

        TextView note = customerView.findViewById(R.id.note);
        TextView customerName = customerView.findViewById(R.id.name);
        TextView customerPhone = customerView.findViewById(R.id.phone);

        note.setText("The Last Customer Start with B");
        customerName.setText("Name: " + name);
        customerPhone.setText("Phone: " + phone);

        customerListLayout.addView(customerView);
    }
}
