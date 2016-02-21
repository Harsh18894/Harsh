package in.daybox.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import butterknife.InjectView;
import in.daybox.R;
import in.daybox.adapters.NewOrderListAdapter;
import in.daybox.constants.NetworkConstants;
import in.daybox.volley.NewOrderItemParse;

/**
 * Created by Dell on 2/20/2016.
 */
public class NewOrderActivity extends AppCompatActivity implements NetworkConstants{

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        listView = (ListView) findViewById(R.id.listNewOrders);
        sendRequest();
    }

    private void sendRequest(){
        StringRequest stringRequest = new StringRequest(NEW_ORDER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NewOrderActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
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
