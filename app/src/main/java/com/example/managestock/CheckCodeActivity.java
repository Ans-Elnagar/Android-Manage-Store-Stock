package com.example.managestock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class CheckCodeActivity extends AppCompatActivity {
    public static Product selectedProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_code);

        //Setting check code button
        Button checkCodeBt = (Button) findViewById(R.id.btCheckCode);
        checkCodeBt.setOnClickListener(v ->{
            String text = ((EditText) findViewById(R.id.txtCheckCode)).getText().toString().trim().toLowerCase();
            int size = MainActivity.allProducts.size();
            boolean notFound = true;
            for(Product product : MainActivity.allProducts){
                if(product.getCode().equals(text)){
                    notFound = false;
                    selectedProduct = product;
                    break;
                }
            }
            if(notFound){
                selectedProduct = new Product();
                selectedProduct.setCode(text);
            }
            // Go to editing this product
            // if this is a new product :
            //      then EditProductActivity will add it to all products and save the list
            Intent intent = new Intent(this, EditProductActivity.class);
            startActivity(intent);
        });
    }
}