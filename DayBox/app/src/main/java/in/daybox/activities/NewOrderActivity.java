package in.daybox.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.daybox.R;
import in.daybox.adapters.NewOrderListAdapter;
import in.daybox.constants.NetworkConstants;
import in.daybox.dto.SessionDTO;
import in.daybox.ui.SnackBar;
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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //parent.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);

                    //view.setBackgroundColor(Color.LTGRAY);
                    AlertDialog.Builder builder = new AlertDialog.Builder(NewOrderActivity.this);
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
                    builder.show();


            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btnNext.setBackgroundResource(R.drawable.ripple);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Are you sure you waana do this.....:X", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sendRequest(){

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

        JsonObjectRequest stringRequest = new JsonObjectRequest (Request.Method.GET, GET_NETWORK_IP+NEW_ORDER_URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {showJSON(response);
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

    private void showJSON(JSONObject json){

        Log.i("Volley", json.toString());

        NewOrderItemParse newOrderItemParse = new NewOrderItemParse(json.toString());
        newOrderItemParse.parseJSON();
        NewOrderListAdapter newOrderListAdapter = new NewOrderListAdapter(this, NewOrderItemParse.id, NewOrderItemParse.name);
        listView.setAdapter(newOrderListAdapter);
    }
}
