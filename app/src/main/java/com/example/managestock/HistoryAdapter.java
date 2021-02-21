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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryAdapter extends ArrayAdapter<OrderContent> {
    Context context;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("LLL dd, yyyy");
    SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
    public HistoryAdapter(@NonNull Context context, @NonNull ArrayList<OrderContent> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.history_list_item, parent, false);
        }
        OrderContent order = getItem(position);

        // Setting Date
        Date d = new Date(order.getTime());
        ((TextView)convertView.findViewById(R.id.textOrderDate)).setText(
                String.format(timeFormatter.format(d) + " " + dateFormatter.format(d)));

        // Setting Details
        String details = "";
        int size = order.size();
        float totalPrice = 0;
        for(int i=0; i<size; i++){
            details += order.getProduct(i).getName();
            details += " x" + order.getQuantity(i);
            float price = order.getQuantity(i) * order.getProduct(i).getSellingPrice();
            totalPrice += price;
            details += "    " + price;
            details += "\n";
        }
        details += "--------------\n";
        details += "Total price : " + totalPrice;
        ((TextView)convertView.findViewById(R.id.textOrderDetails)).setText(details);
        return convertView;
    }
}
