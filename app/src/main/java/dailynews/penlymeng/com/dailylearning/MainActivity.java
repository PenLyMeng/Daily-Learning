package dailynews.penlymeng.com.dailylearning;

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

import dailynews.penlymeng.com.dailylearning.adapter.MainViewPagerAdapter;
import dailynews.penlymeng.com.dailylearning.model.SourceNew;
import dailynews.penlymeng.com.dailylearning.service.CoreService;
import dailynews.penlymeng.com.dailylearning.service.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static final String TAG = "ooo";

    MainViewPagerAdapter mainViewPagerAdapter;

    ViewPager mViewPagerContainer;
    TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mViewPagerContainer.setAdapter(mainViewPagerAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Recent News"));
        tabLayout.addTab(tabLayout.newTab().setText("Top News"));
        tabLayout.addTab(tabLayout.newTab().setText("Source News"));

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
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_fiber_pin_pink_a200_24dp);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_dashboard_pink_a200_24dp);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tab.setIcon(R.drawable.ic_fiber_new_black_24dp);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_fiber_pin_black_24dp);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_dashboard_black_24dp);
                        break;

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        ServiceGenerator.setBaseUrl(getResources().getString(R.string.base_url_news));
        CoreService coreService = ServiceGenerator.createService(CoreService.class);


        coreService.listSourceNews(getString(R.string.api_key)).enqueue(new Callback<SourceNew>() {
            @Override
            public void onResponse(Call<SourceNew> call, Response<SourceNew> response) {
                Log.d(TAG, "onResponse: " + response.body().sources.size());
            }

            @Override
            public void onFailure(Call<SourceNew> call, Throwable t) {
                Log.d(TAG, "onError: " + t.getMessage());
            }
        });





    }




    public void initViews(){
        mViewPagerContainer = findViewById(R.id.viewpager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


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

        } else if (id == R.id.nav_about_us) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
