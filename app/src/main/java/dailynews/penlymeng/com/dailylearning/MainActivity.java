package dailynews.penlymeng.com.dailylearning;

import android.content.Intent;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

import org.rm3l.maoni.Maoni;

import dailynews.penlymeng.com.dailylearning.adapter.MainViewPagerAdapter;
import dailynews.penlymeng.com.dailylearning.callback.ListNewsItemListener;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SensorEventListener,ListNewsItemListener {


    public static final String TAG = "ooo";

    MainViewPagerAdapter mainViewPagerAdapter;

    ViewPager mViewPagerContainer;
    TabLayout tabLayout;
    SensorManager mSensorManager;

    private long lastUpdate;

    FloatingActionButton mFloatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();


        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mViewPagerContainer.setAdapter(mainViewPagerAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Recent News"));
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab().setText(""));

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_fiber_new_pink_a200_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_fiber_pin_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_dashboard_black_24dp);


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPagerContainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPagerContainer.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()){
                    case 0:
                        tab.setIcon(R.drawable.ic_fiber_new_pink_a200_24dp);
                        tab.setText("Recent News");
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_fiber_pin_pink_a200_24dp);
                        tab.setText("Top News");
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_dashboard_pink_a200_24dp);
                        tab.setText("Source News");
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tab.setIcon(R.drawable.ic_fiber_new_black_24dp);
                        tab.setText("");

                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_fiber_pin_black_24dp);
                        tab.setText("");

                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_dashboard_black_24dp);
                        tab.setText("");

                        break;

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Maoni.Builder(MainActivity.this, "Penlymeng")
                        .withDefaultToEmailAddress("menglovegood@gmail.com")
                        .build()
                        .start(MainActivity.this);
            }
        });



    }



    private void getAccelerometer(SensorEvent event) {
         float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;
        if (accelationSquareRoot >= 2) //
        {

            Log.d("time space", actualTime - lastUpdate + " ");

            if (actualTime - lastUpdate > 10000) {
                lastUpdate = actualTime;
                Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
                        .show();
             }




       /*new Maoni.Builder(this, "Penlymeng")
                    .withDefaultToEmailAddress("menglovegood@gmail.com")
                    .build()
                    .start(MainActivity.this); */

        }





    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void initViews(){

        mFloatingActionButton = findViewById(R.id.fab_email);
        mViewPagerContainer = findViewById(R.id.viewpager);
        mViewPagerContainer.setOffscreenPageLimit(2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        tabLayout =  findViewById(R.id.sliding_tabs);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            // Handle the camera action
        } else if (id == R.id.nav_videos) {

        } else if (id == R.id.nav_podcast) {

        }else if (id == R.id.nav_weather) {

        }else if (id == R.id.nav_feedback) {
            new Maoni.Builder(this, "Penlymeng")
            .withDefaultToEmailAddress("menglovegood@gmail.com")
                    .build()
                    .start(MainActivity.this);
        } else if (id == R.id.nav_about_us) {
            startActivity(new Intent(this,AboutUsActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onNewsClickListener(int position) {
        Toast.makeText(this, "position " + position, Toast.LENGTH_SHORT)
                .show();
    }
}
