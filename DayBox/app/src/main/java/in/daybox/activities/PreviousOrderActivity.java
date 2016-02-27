package in.daybox.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import butterknife.InjectView;
import in.daybox.R;
import in.daybox.adapters.PreviousOrderListAdapter;

/**
 * Created by Dell on 2/27/2016.
 */
public class PreviousOrderActivity extends AppCompatActivity {

    @InjectView(R.id.listPreviousOrders)
    ExpandableListView listPreviousOrders;


    private List<String> listHeader;
    private HashMap<String, List<String>> listChild;
    private PreviousOrderListAdapter previousOrderListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_orders);
        populate();

    }

    private void populate() {
        prepareList();
        previousOrderListAdapter = new PreviousOrderListAdapter(this, listHeader, listChild);
        listPreviousOrders.setAdapter(previousOrderListAdapter);
        listPreviousOrders.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        listPreviousOrders.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), listHeader.get(groupPosition) + "Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        listPreviousOrders.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), listHeader.get(groupPosition)+"Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareList() {

    }
}
