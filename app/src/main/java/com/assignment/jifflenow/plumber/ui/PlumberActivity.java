package com.assignment.jifflenow.plumber.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.assignment.jifflenow.R;
import com.assignment.jifflenow.common.ui.BaseActivity;
import com.assignment.jifflenow.common.ui.RecyclerViewFragment;
import com.assignment.jifflenow.plumber.PlumberManager;
import com.assignment.jifflenow.utils.Constants;
import com.assignment.jifflenow.utils.FragmentHelper;

public class PlumberActivity extends BaseActivity implements RecyclerViewFragment.ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_home);
        FragmentHelper.addFragment(this, new WeekdaysFragment(), R.id.container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWeekDayClicked(String day) {
        Fragment fragment = new AppointmentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BundleKeys.DAY, day);
        fragment.setArguments(bundle);
        FragmentHelper.replaceAndAddFragment(this, fragment, R.id.container);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlumberManager.clear();
    }
}
