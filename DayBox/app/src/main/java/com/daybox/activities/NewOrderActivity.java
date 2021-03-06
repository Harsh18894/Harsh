package com.daybox.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daybox.R;
import com.daybox.adapters.NewOrderListAdapter;
import com.daybox.constants.NetworkConstants;
import com.daybox.dto.OrderDTO;
import com.daybox.dto.SessionDTO;
import com.daybox.global.GlobalData;
import com.daybox.ui.TypefaceSpan;
import com.daybox.volley.NewOrderItemParse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Dell on 2/20/2016.
 */
public class NewOrderActivity extends AppCompatActivity implements NetworkConstants {

    @InjectView(R.id.listNewOrders)
    ListView listView;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.btnNext)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_order);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        SpannableString s = new SpannableString("Place a New Order");
        s.setSpan(new TypefaceSpan(this, "LatoLatin-Regular.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) toolbar.findViewById(R.id.toolbarTitle)).setText(s);
        getSupportActionBar().setTitle("");
        sendRequest();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //parent.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);

                //view.setBackgroundColor(Color.LTGRAY);
                    /*AlertDialog.Builder builder = new AlertDialog.Builder(NewOrderActivity.this);
                    builder.setTitle("Enter Quantity");
                    EditText editText = new EditText(NewOrderActivity.this);
                    editText.setHint("Quantity..");
                    builder.setView(editText);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.setNegativeButton("Cancel", null);
                    builder.show();*/
                new MaterialDialog.Builder(NewOrderActivity.this)
                        .title("Enter Quantity")
                        .inputType(InputType.TYPE_NUMBER_FLAG_SIGNED)
                        .input("Enter Quantity", null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                String data = input.toString().trim();
                                if (data.equals("")) {
                                    Toast.makeText(NewOrderActivity.this, "Enter some quantity", Toast.LENGTH_SHORT).show();
                                } else {
                                    OrderDTO orderDTO = new OrderDTO();
                                    orderDTO.setQty(input.toString());
                                    orderDTO.setName(NewOrderItemParse.name[position]);
                                    orderDTO.setId(NewOrderItemParse.id[position]);
                                    if (null == ((GlobalData) getApplicationContext()).getOrderDTOs()) {
                                        ((GlobalData) getApplicationContext()).setOrderDTOs(new ArrayList<OrderDTO>());
                                        ((GlobalData) getApplicationContext()).getOrderDTOs().add(orderDTO);
                                    } else
                                        ((GlobalData) getApplicationContext()).getOrderDTOs().add(orderDTO);
                                }
                            }
                        }).show();


            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btnNext.setBackgroundResource(R.drawable.ripple);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == ((GlobalData) getApplicationContext()).getOrderDTOs())
                    ((GlobalData) getApplicationContext()).setOrderDTOs(new ArrayList<OrderDTO>());
                else{
                    if(((GlobalData)getApplicationContext()).getOrderDTOs().size() != 0){
                       /* */

                        Intent intent = new Intent(NewOrderActivity.this, PlaceOrderActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });
    }

    private void sendRequest() {

        JSONObject params = new JSONObject();
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SessionDTO sessionDTO = new Gson().fromJson(sharedPreferences.getString("session", null), SessionDTO.class);
            params.put("id", sessionDTO.getId());
            params.put("mobile", sessionDTO.getMobile());
            params.put("accessTokenId", sessionDTO.getAccessTokenId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, GET_NETWORK_IP + NEW_ORDER_URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NewOrderActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(JSONObject json) {

        Log.i("Volley", json.toString());

        NewOrderItemParse newOrderItemParse = new NewOrderItemParse(json.toString());
        newOrderItemParse.parseJSON();
        NewOrderListAdapter newOrderListAdapter = new NewOrderListAdapter(this, NewOrderItemParse.id, NewOrderItemParse.name);
        listView.setAdapter(newOrderListAdapter);
    }
}
