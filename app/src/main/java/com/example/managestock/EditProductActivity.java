package com.example.managestock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProductActivity extends AppCompatActivity {

    EditText nameEdit;
    EditText codeEdit;
    AutoCompleteTextView categoryEdit;
    EditText quantityEdit;
    EditText descriptionEdit;
    EditText buyingPriceEdit;
    EditText sellingPriceEdit;
    EditText piecesInPackageEdit;

    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        nameEdit = (EditText) findViewById(R.id.editName);
        codeEdit = (EditText) findViewById(R.id.editCode);
        categoryEdit = (AutoCompleteTextView) findViewById(R.id.editCategory);
        quantityEdit = (EditText) findViewById(R.id.editQuantity);
        descriptionEdit = (EditText) findViewById(R.id.editDiscription);
        buyingPriceEdit = (EditText) findViewById(R.id.editBuyingPrice);
        sellingPriceEdit = (EditText) findViewById(R.id.editSellingPrice);
        piecesInPackageEdit = (EditText) findViewById(R.id.editPiecesInPackage);

        //Setting category options
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,MainActivity.categories);
        categoryEdit.setThreshold(1);//will start working from first character
        categoryEdit.setAdapter(adapter);

        Product product = CheckCodeActivity.selectedProduct;

        // Filling Fields
        nameEdit.setText(product.getName());
        codeEdit.setText(product.getCode());
        if( ! product.getCategory().equals(""))
            categoryEdit.setText(product.getCategory());
        quantityEdit.setText(product.getQuantity() + "");
        descriptionEdit.setText(product.getDescription());
        buyingPriceEdit.setText(product.getBuyingPrice()+ "");
        sellingPriceEdit.setText(product.getSellingPrice() + "");
        piecesInPackageEdit.setText(product.getPiecesInPackage() + "");

        //Setting Save button
        Button saveBt = (Button)findViewById(R.id.btSave);
        saveBt.setOnClickListener(v -> {
            // Product name
            product.setName(nameEdit.getText().toString().trim());
            product.setCode(codeEdit.getText().toString().trim().toLowerCase());
            product.setCategory(categoryEdit.getText().toString().trim().toLowerCase());
            product.setQuantity(Integer.parseInt(quantityEdit.getText().toString()));
            product.setDescription(descriptionEdit.getText().toString().trim());
            product.setBuyingPrice(Float.parseFloat(buyingPriceEdit.getText().toString()));
            product.setSellingPrice(Float.parseFloat(sellingPriceEdit.getText().toString()));
            product.setPiecesInPackage(Integer.parseInt(piecesInPackageEdit.getText().toString()));

            if( ! MainActivity.allProducts.contains(product)){
                MainActivity.allProducts.add(product);
                FileTools.getInstance().saveAllProducts(getApplicationContext());
            }
            if( ! MainActivity.categories.contains(product.getCategory())){
                MainActivity.categories.add(product.getCategory());
                FileTools.getInstance().saveCategories(getApplicationContext());
                MainActivity.spinnerAdapter.notifyDataSetChanged();
            }
            Toast.makeText(getApplicationContext(),"Product is added successfully.",Toast.LENGTH_LONG).show();
            finish();
        });
    }
}