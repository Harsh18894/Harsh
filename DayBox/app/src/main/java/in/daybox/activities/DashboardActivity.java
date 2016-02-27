package in.daybox.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.daybox.R;
import in.daybox.adapters.DashboardListAdapter;
import in.daybox.dto.DashboardListItemDTO;
import in.daybox.dto.MessageCustomDialogDTO;
import in.daybox.ui.SnackBar;
import in.daybox.util.NetworkCheck;

/**
 * Created by Dell on 2/16/2016.
 */
public class DashboardActivity extends AppCompatActivity {

    @InjectView(R.id.listDashboard)
    ListView listDashboard;
    private Toolbar toolbar;
    @InjectView(R.id.btnLogout)
    Button btnLogout;
    //private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;
    private DashboardListItemDTO dashboardListItemDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        listDashboard = (ListView) findViewById(R.id.listDashboard);
        populate();
    }

    private void populate() {
        ButterKnife.inject(this);
        toolbar = (Toolbar) findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedEditor = sharedPreferences.edit();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btnLogout.setBackgroundResource(R.drawable.ripple_red);
        }


        btnLogout.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             sharedEditor.putBoolean("isLogin", false);
                                             sharedEditor.putString("session", null);
                                             sharedEditor.commit();

                                             Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                                             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                             Bundle bundle = new Bundle();
                                             bundle.putString("logout", "Logout");
                                             intent.putExtras(bundle);
                                             startActivity(intent);

                                         }
                                     }

        );

/*             LogoutAsyncTask logoutAsyncTask = new LogoutAsyncTask(getApplicationContext());
                            logoutAsyncTask.execute();
               *//*
         }
                    });

                    mMaterialDialog.setNegativeButton("CANCEL", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });

                    mMaterialDialog.show();
                } else {
                    MessageCustomDialogDTO messageCustomDialogDTO = new MessageCustomDialogDTO();
                    messageCustomDialogDTO.setTitle(getResources().getString(R.string.login_activity_no_internet_title));
                    messageCustomDialogDTO.setButton(getResources().getString(R.string.ok));
                    messageCustomDialogDTO.setMessage(getResources().getString(R.string.login_activity_no_internet));
                    messageCustomDialogDTO.setContext(getApplicationContext());
                    SnackBar.show(getApplicationContext(), messageCustomDialogDTO);
                }
            }
        });
*/


        /*DashboardListAdapter dashboardListAdapter = new DashboardListAdapter(getApplicationContext(), getData());
        listDashboard.setAdapter(dashboardListAdapter);
        *//*SpannableString s = new SpannableString("Home");
        s.setSpan(new TypefaceSpan(this, "LatoLatin-Regular.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) toolbar.findViewById(R.id.toolbarTitle)).setText(s);

        getSupportActionBar().setTitle("");
*/

        List<DashboardListItemDTO> dashboardListItemDTOs = new ArrayList<>();
        DashboardListAdapter dashboardListAdapter = new DashboardListAdapter(getApplicationContext(), dashboardListItemDTOs);

        DashboardListItemDTO dashboardListItemDTO = new DashboardListItemDTO();
        dashboardListItemDTO.setTitle(getResources().getString(R.string.NewOrder));
        dashboardListItemDTO.setDesc(getResources().getString(R.string.NewOrderDesc));
        dashboardListItemDTO.setImage(R.mipmap.new_orders);

        dashboardListItemDTOs.add(dashboardListItemDTO);
        dashboardListItemDTO = new DashboardListItemDTO();
        dashboardListItemDTO.setTitle(getResources().getString(R.string.PreviousOrder));
        dashboardListItemDTO.setDesc(getResources().getString(R.string.PreviousOrderDesc));
        dashboardListItemDTO.setImage(R.mipmap.previous_orders);

        dashboardListItemDTOs.add(dashboardListItemDTO);
        dashboardListItemDTO = new
                DashboardListItemDTO();

        dashboardListItemDTO.setTitle(getResources().getString(R.string.CustomerCare));
        dashboardListItemDTO.setDesc(getResources().getString(R.string.CustomerCareDesc));
        dashboardListItemDTO.setImage(R.mipmap.customer_care);

        dashboardListItemDTOs.add(dashboardListItemDTO);

        listDashboard.setAdapter(dashboardListAdapter);

        listDashboard.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                             {
                                                 @Override
                                                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                     int itemPosition = position;

                                                     switch (itemPosition) {
                                                         case 0:
                                                             if (NetworkCheck.isNetworkAvailable(DashboardActivity.this)) {
                                                                 Intent intent = new Intent(DashboardActivity.this, NewOrderActivity.class);
                                                                 startActivity(intent);
                                                             } else {
                                                                 MessageCustomDialogDTO messageCustomDialogDTO = new MessageCustomDialogDTO();
                                                                 messageCustomDialogDTO.setTitle(getResources().getString(R.string.login_activity_no_internet_title));
                                                                 messageCustomDialogDTO.setButton(getResources().getString(R.string.ok));
                                                                 messageCustomDialogDTO.setMessage(getResources().getString(R.string.login_activity_no_internet));
                                                                 messageCustomDialogDTO.setContext(DashboardActivity.this);
                                                                 SnackBar.show(DashboardActivity.this, messageCustomDialogDTO);
                                                             }
                                                             break;
                                                         case 1:
                                                             Toast.makeText(getApplicationContext(), "No activity defined yet", Toast.LENGTH_SHORT).show();
                                                             break;

                                                         case 2:
                                                             Intent intent = new Intent(Intent.ACTION_DIAL);
                                                             intent.setData(Uri.parse("tel:9619851835"));
                                                             startActivity(intent);
                                                             break;
                                                     }
                                                 }
                                             }

        );
    }
}
    /*private List<DashboardListItemDTO> getData() {
        List<DashboardListItemDTO> dashboardListItemDTOs = new ArrayList<>();

        DashboardListItemDTO dashboardListItemDTO = new DashboardListItemDTO();
        dashboardListItemDTO.setTitle(getResources().getString(R.string.NewOrder));
        dashboardListItemDTO.setDesc(getResources().getString(R.string.NewOrderDesc));
        dashboardListItemDTO.setImage(R.mipmap.ic_launcher);

        dashboardListItemDTOs.add(dashboardListItemDTO);
        dashboardListItemDTO = new DashboardListItemDTO();
        dashboardListItemDTO.setTitle(getResources().getString(R.string.PreviousOrder));
        dashboardListItemDTO.setDesc(getResources().getString(R.string.PreviousOrderDesc));
        dashboardListItemDTO.setImage(R.mipmap.ic_launcher);

        dashboardListItemDTOs.add(dashboardListItemDTO);
        dashboardListItemDTO = new DashboardListItemDTO();
        dashboardListItemDTO.setTitle(getResources().getString(R.string.CustomerCare));
        dashboardListItemDTO.setDesc(getResources().getString(R.string.CustomerCareDesc));
        dashboardListItemDTO.setImage(R.mipmap.ic_launcher);

        return dashboardListItemDTOs;
    }*/
