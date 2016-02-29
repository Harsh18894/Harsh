package com.daybox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;

import com.daybox.R;
import com.daybox.dto.OrderDTO;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Dell on 2/25/2016.
 */
public class PreviousOrderListAdapter extends BaseAdapter {


    private Context context;
    private List<OrderDTO> orders;


    public PreviousOrderListAdapter(Context context, List<OrderDTO> orderDTOs){
        this.context = context;
        this.orders = orderDTOs;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View previousOrderItemList = inflater.inflate(R.layout.activity_previous_orders_by_date_single_row, null);
        TextView txtItemId = (TextView) previousOrderItemList.findViewById(R.id.txtItemId);
        TextView txtOrderName = (TextView) previousOrderItemList.findViewById(R.id.txtOrderName);
        TextView txtOrderAmt = (TextView) previousOrderItemList.findViewById(R.id.txtOrderAmt);


        txtItemId.setText(orders.get(position).getId());
        txtOrderName.setText(orders.get(position).getName());
        txtOrderAmt.setText(orders.get(position).getQty());


        return previousOrderItemList;
    }}
