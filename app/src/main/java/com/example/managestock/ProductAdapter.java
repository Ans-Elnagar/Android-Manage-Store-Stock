package com.example.managestock;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ClipboardManager clipboard;
    Context context;
    public ProductAdapter(@NonNull Context context, @NonNull ArrayList<Product> objects, ClipboardManager clipboard ) {
        super(context, 0, objects);
        this.clipboard = clipboard;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Product product = getItem(position);

        //Setting list item values
        ((TextView) convertView.findViewById(R.id.textName)).setText(product.getName());
        TextView codeText = (TextView) convertView.findViewById(R.id.textCode);
        codeText.setText(product.getCode());
        ((TextView) convertView.findViewById(R.id.textQuantity)).setText("Quantity: "+product.getQuantity());
        ((TextView) convertView.findViewById(R.id.textPrice)).setText("Price: "+product.getSellingPrice());
        ((TextView) convertView.findViewById(R.id.textPiecesInPackage)).setText("Number of Pieces per package: "+product.getPiecesInPackage());


        // Setting order quantity edit text
        int q = MainActivity.currentOrder.getQuantity(product);
        String t = "";
        if(q != 0)
            t += q;
        ((TextView) convertView.findViewById(R.id.editOrderQuantity)).setText(t);

        //Setting when pressing the text code it will be copied
        codeText.setOnClickListener(v -> {
            ClipData clip = ClipData.newPlainText(product.getCode(), product.getCode());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Code:" + product.getCode() + " is copied to clipboard",Toast.LENGTH_LONG).show();
        });

        //Setting add to order content button
        View finalConvertView = convertView;
        ((Button) convertView.findViewById(R.id.btAddToOrderContent)).setOnClickListener(v -> {
            TextView orderField = (TextView) finalConvertView.findViewById(R.id.editOrderQuantity);
            String orderTextValue = orderField.getText().toString();
            if(orderTextValue == null || orderTextValue.equals("") || orderTextValue.equals("0")){
                MainActivity.currentOrder.remove(product);
                orderField.setText("");
            }else{
                int orderQuantity = Integer.parseInt(orderTextValue);
                if(orderQuantity <= product.getQuantity()) {
                    MainActivity.currentOrder.add(product, orderQuantity);
                    Toast.makeText(context, "Quantity added to the list successfully.", Toast.LENGTH_SHORT).show();
                }else{
                    int oldQuantity = MainActivity.currentOrder.getQuantity(product);
                    String oldText = "";
                    if(oldQuantity != 0)
                        oldText += oldQuantity;
                    orderField.setText(oldText);
                    Toast.makeText(context, "Failed. The order quantity is more than the available quantity."
                            + "\n The Field reset its value.", Toast.LENGTH_LONG).show();
                }
            }
        });

        return convertView;
    }
}

