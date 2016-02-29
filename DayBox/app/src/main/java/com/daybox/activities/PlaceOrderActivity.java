package com.daybox.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.daybox.R;
import com.daybox.adapters.OrdersListAdapter;
import com.daybox.asynctask.PlaceOrderAsyncTask;
import com.daybox.global.GlobalData;
import com.daybox.ui.TypefaceSpan;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by adityaagrawal on 28/02/16.
 */
public class PlaceOrderActivity extends AppCompatActivity {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.listOrders)
    ListView listOrders;
    @InjectView(R.id.btnNext)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        SpannableString s = new SpannableString("Confirm Order");
        s.setSpan(new TypefaceSpan(this, "LatoLatin-Regular.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) toolbar.findViewById(R.id.toolbarTitle)).setText(s);
        getSupportActionBar().setTitle("");


        OrdersListAdapter ordersListAdapter = new OrdersListAdapter(this, ((GlobalData)getApplicationContext()).getOrderDTOs());
        listOrders.setAdapter(ordersListAdapter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceOrderAsyncTask placeOrderAsyncTask = new PlaceOrderAsyncTask(PlaceOrderActivity.this, ((GlobalData)getApplicationContext()).getOrderDTOs());
                placeOrderAsyncTask.execute();
            }
        });
    }
}
