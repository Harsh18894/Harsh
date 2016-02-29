package com.daybox.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.daybox.R;
import com.neopixl.pixlui.components.textview.TextView;


/**
 * Created by Dell on 2/20/2016.
 */
public class NewOrderListAdapter extends ArrayAdapter<String> {

    private String[] id;
    private String[] name;
    private Activity context;


    public NewOrderListAdapter(Activity context, String[] id, String[] name){
        super(context, R.layout.activity_new_order_single_item_list, id);
        this.context = context;
        this.id = id;
        this.name = name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View newOrderItemList = inflater.inflate(R.layout.activity_new_order_single_item_list, null);
        TextView txtItemStatus = (TextView) newOrderItemList.findViewById(R.id.txtItemStatus);
        TextView txtItemName = (TextView) newOrderItemList.findViewById(R.id.txtItemName);

        txtItemStatus.setText(id[position]);
        txtItemName.setText(name[position]);

        return newOrderItemList;
    }
}
