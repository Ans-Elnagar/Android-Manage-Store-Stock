package com.example.managestock;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CategoryActivity extends AppCompatActivity {


    ClipboardManager clipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        //Setting back button
        ((Button) findViewById(R.id.btBack)).setOnClickListener(v -> {
            finish();
        });

        // Setting order button
        ((Button) findViewById(R.id.btOrder)).setOnClickListener(v -> {
            OrderContent order = MainActivity.currentOrder;
            int size = order.size();
            for(int i=0; i<size; i++){
                Product p = order.getProduct(i);
                p.setQuantity( p.getQuantity() - order.getQuantity(i) );
            }
            order.setTime(System.currentTimeMillis());
            MainActivity.history.add(order);
            MainActivity.currentOrder = new OrderContent();
            FileTools.getInstance().saveAllProducts(getApplicationContext());
            FileTools.getInstance().saveHistory(getApplicationContext());
            Toast.makeText(this, "Order is made successfully.", Toast.LENGTH_LONG).show();
            finish();
        });

        // Setting category text view
        String category = MainActivity.selectedCategory;
        ((TextView)findViewById(R.id.txtCategory)).setText(category);


        // preparing category list
        ArrayList<Product> list = new ArrayList<>();
        for(Product product : MainActivity.allProducts){
            if(product.getCategory().equals(category))
                list.add(product);
        }


        // Setting the list
        ListView productListView = (ListView) findViewById(R.id.productListView);
        ProductAdapter adapter = new ProductAdapter(this, list, clipboard);
        productListView.setAdapter(adapter);
    }
}