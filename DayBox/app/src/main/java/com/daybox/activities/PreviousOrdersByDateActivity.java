package com.daybox.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daybox.R;
import com.daybox.adapters.NewOrderListAdapter;
import com.daybox.adapters.PreviousOrderListAdapter;
import com.daybox.constants.NetworkConstants;
import com.daybox.dto.SessionDTO;
import com.daybox.global.GlobalData;
import com.daybox.ui.TypefaceSpan;
import com.daybox.volley.NewOrderItemParse;
import com.daybox.volley.PreviousOrderItemParse;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Dell on 2/29/2016.
 */
public class PreviousOrdersByDateActivity extends AppCompatActivity implements NetworkConstants{

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.previousOrderList)
    ListView previousOrderList;
    private CalendarDay date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_previous_orders_by_date);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        SpannableString s = new SpannableString("Your Order");
        s.setSpan(new TypefaceSpan(this, "LatoLatin-Regular.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) toolbar.findViewById(R.id.toolbarTitle)).setText(s);
        getSupportActionBar().setTitle("");

        sendRequest();
/*
        PreviousOrderListAdapter previousOrderListAdapter = new PreviousOrderListAdapter(this, ((GlobalData)getApplicationContext()).getOrderDTOs());
        previousOrderList.setAdapter(previousOrderListAdapter);
*/

    }

    private void sendRequest() {
        JSONObject params = new JSONObject();
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SessionDTO sessionDTO = new Gson().fromJson(sharedPreferences.getString("session", null), SessionDTO.class);
            params.put("id", sessionDTO.getId());
            params.put("mobile", sessionDTO.getMobile());
            params.put("accessTokenId", sessionDTO.getAccessTokenId());
            Bundle bundle = getIntent().getExtras();
            params.put("date", bundle);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, GET_NETWORK_IP + PREVIOUS_ORDERS_URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PreviousOrdersByDateActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(JSONObject json) {

        Log.i("Volley", json.toString());

        PreviousOrderItemParse previousOrderItemParse = new PreviousOrderItemParse(json.toString());
        previousOrderItemParse.parseJSON();
        PreviousOrderListAdapter previousOrderListAdapter = new PreviousOrderListAdapter(this, ((GlobalData)getApplicationContext()).getOrderDTOs());
        previousOrderList.setAdapter(previousOrderListAdapter);
        /*PreviousOrderListAdapter previousOrderListAdapter = new PreviousOrderListAdapter(this, PreviousOrderItemParse.id, PreviousOrderItemParse.name, PreviousOrderItemParse.qty);
        previousOrderList.setAdapter(previousOrderListAdapter);*/
    }

}
