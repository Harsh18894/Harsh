package com.daybox.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.WindowManager;
import android.widget.TextView;

import com.daybox.R;
import com.daybox.ui.TypefaceSpan;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Dell on 28/02/16.
 */
public class PreviousOrdersActivity extends AppCompatActivity{

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.calendarView)
    MaterialCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_orders);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        SpannableString s = new SpannableString("View Your Previous Orders");
        s.setSpan(new TypefaceSpan(this, "LatoLatin-Regular.ttf"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) toolbar.findViewById(R.id.toolbarTitle)).setText(s);
        getSupportActionBar().setTitle("");

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                Bundle bundle = new Bundle();
                bundle.putString("date", date.toString());
                Intent intent = new Intent(PreviousOrdersActivity.this, PreviousOrdersByDateActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
