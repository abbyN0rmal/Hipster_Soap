package com.teamedge.android.hipster_soap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox lavenderCheckBox = (CheckBox) findViewById(R.id.lavender);
        boolean hasLavender = lavenderCheckBox.isChecked();

        CheckBox peppermintCheckBox = (CheckBox) findViewById(R.id.peppermint);
        boolean hasPeppermint = peppermintCheckBox.isChecked();

        CheckBox roseCheckBox = (CheckBox) findViewById(R.id.rose);
        boolean hasRose = roseCheckBox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.name);

        displayQuantity(quantity);
        int price = calculatePrice(hasLavender, hasPeppermint, hasRose);
        displayPrice(price);

        String orderMessage = "Name: " + nameEditText.getText().toString() + "\n" +
                "Add Lavender? " + hasLavender + "\n" +
                "Add Peppermint? " + hasPeppermint + "\n" +
                "Add Rose Petals? " + hasRose + "\n" +
                "Number of Soaps You Ordered: " + quantity + "\n" +
                "Total Price: $" + price;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: ")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Soap Shop");
        intent.putExtra(Intent.EXTRA_TEXT, orderMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("\uD835\uDE80\uD835\uDE9E\uD835\uDE8A\uD835\uDE97\uD835\uDE9D\uD835\uDE92\uD835\uDE9D\uD835\uDEA2\n"+ number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("Total Price: $" + number);
    }



    public void increase(View view){
        quantity ++;
        displayQuantity(quantity);
    }

    public void decrease(View view){
        quantity --;
        displayQuantity(quantity);
    }

    private int calculatePrice(boolean addLavender, boolean addPeppermint, boolean addRosePetal) {
        int price = quantity * 5 ;

        if (addLavender == true) {
            price = price + 5;
        }

        if (addPeppermint == true) {
            price += 2;
        }

        if (addRosePetal == true){
            price += 7;
        }

        return price;
    }
}
