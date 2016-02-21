package in.daybox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.daybox.R;
import in.daybox.dto.DashboardListItemDTO;
import in.daybox.ui.CustomTextView;

/**
 * Created by Dell on 2/16/2016.
 */
public class DashboardListAdapter extends BaseAdapter {

    private Context context;
    private List<DashboardListItemDTO> items;
    private LayoutInflater inflater;

    public DashboardListAdapter(Context context, List<DashboardListItemDTO> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_dashboard_list_single_row, null);
            ((TextView)convertView.findViewById(R.id.txtListDashboard)).setText(items.get(position).getTitle());
            ((TextView)convertView.findViewById(R.id.txtListDesc)).setText(items.get(position).getDesc());
            ((ImageView)convertView.findViewById(R.id.imgListIcon)).setImageResource(items.get(position).getImage());
            return convertView;
    }
}
