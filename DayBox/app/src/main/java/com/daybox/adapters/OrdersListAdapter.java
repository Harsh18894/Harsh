package com.daybox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.daybox.R;
import com.daybox.dto.OrderDTO;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.List;


/**
 * Created by Dell on 2/20/2016.
 */
public class OrdersListAdapter  extends BaseAdapter {

    private Context context;
    private List<OrderDTO> orders;


    public OrdersListAdapter(Context context, List<OrderDTO> orderDTOs){
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
        View newOrderItemList = inflater.inflate(R.layout.activity_new_order_single_item_list, null);
        TextView txtItemStatus = (TextView) newOrderItemList.findViewById(R.id.txtItemStatus);
        TextView txtItemName = (TextView) newOrderItemList.findViewById(R.id.txtItemName);

        txtItemStatus.setText(orders.get(position).getQty());
        txtItemName.setText(orders.get(position).getName());

        return newOrderItemList;
    }
}
