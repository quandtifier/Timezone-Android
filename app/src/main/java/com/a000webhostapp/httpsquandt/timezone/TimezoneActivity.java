package com.a000webhostapp.httpsquandt.timezone;


import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.a000webhostapp.httpsquandt.timezone.Timezone.TimezoneContent;

public class TimezoneActivity extends AppCompatActivity
    implements TimezoneListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timezone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.timezone_fragment_container, new TimezoneListFragment())
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timezone, menu);
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
    public void onListFragmentInteraction(TimezoneContent.TimezoneItem item) {
        TimezoneDetailFragment timezoneDetailFragment = null;
        if(findViewById(R.id.timezone_fragment_container) == null) {
//            timezoneDetailFragment = (TimezoneDetailFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.timezone_detail_frag);
//            timezoneDetailFragment.updateTimezoneItemView(item);
        } else {
            timezoneDetailFragment = TimezoneDetailFragment.getCourseDetailFragment(item);

            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.timezone_fragment_container, timezoneDetailFragment)
                    .addToBackStack(null);

            transaction.commit();
        }
    }
}
