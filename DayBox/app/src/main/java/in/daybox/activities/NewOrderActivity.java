package in.daybox.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.daybox.R;
import in.daybox.adapters.NewOrderListAdapter;
import in.daybox.constants.NetworkConstants;
import in.daybox.ui.TypefaceSpan;
import in.daybox.volley.NewOrderItemParse;

/**
 * Created by Dell on 2/20/2016.
 */
public class NewOrderActivity extends AppCompatActivity implements NetworkConstants{

    @InjectView(R.id.listNewOrders)
    ListView listView;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

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
    }

    private void sendRequest(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_NETWORK_IP+NEW_ORDER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {showJSON(response);
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

    private void showJSON(String json){

        NewOrderItemParse newOrderItemParse = new NewOrderItemParse(json);
        newOrderItemParse.parseJSON();
        NewOrderListAdapter newOrderListAdapter = new NewOrderListAdapter(this, NewOrderItemParse.id, NewOrderItemParse.name);
        listView.setAdapter(newOrderListAdapter);
    }
}
