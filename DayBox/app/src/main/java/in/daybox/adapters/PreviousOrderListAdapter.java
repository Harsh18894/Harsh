package in.daybox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;

import com.neopixl.pixlui.components.textview.TextView;

import java.util.HashMap;
import java.util.List;

import in.daybox.R;

/**
 * Created by Dell on 2/25/2016.
 */
public class PreviousOrderListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeader;
    private HashMap<String, List<String>> listChild;

    public PreviousOrderListAdapter(Context context, List<String> listHeader, HashMap<String, List<String>> listChild){

        this.context = context;
        this.listHeader = listHeader;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return this.listHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listChild.get(this.listHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listChild.get(this.listHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_previous_order_list_group, null);
        }
        TextView txtPreviousOrderList = (TextView) convertView.findViewById(R.id.txtPreviousOrderList);
        txtPreviousOrderList.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_previous_order_single_list_item, null);
        }

        TextView txtOrder = (TextView) convertView.findViewById(R.id.txtOrder);

        TextView txtOrderAmount = (TextView) convertView.findViewById(R.id.txtOrderAmount);

        Button btnIssue = (Button) convertView.findViewById(R.id.btnIssue);

        txtOrder.setText(childText);
        txtOrderAmount.setText(childText);
        btnIssue.setText("Report Issue");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
