package com.afn.afnapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.afn.afnapp.fragment.FragmentBeranda;
import com.afn.afnapp.fragment.FragmentFeedPostingan;
import com.afn.afnapp.fragment.FragmentProfil;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private LinearLayout llInstagram;
    private LinearLayout llFacebook;
    private LinearLayout llTwitter;
    private LinearLayout llTelegram;
    private LinearLayout llYoutube;
    private LinearLayout llBeranda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        llBeranda = headerView.findViewById(R.id.llBeranda);
        llFacebook = headerView.findViewById(R.id.llFacebook);
        llTwitter = headerView.findViewById(R.id.llTwitter);
        llInstagram = headerView.findViewById(R.id.llInstagram);
        llYoutube = headerView.findViewById(R.id.llYoutube);
        llTelegram = headerView.findViewById(R.id.llTelegram);

        tampilkanFragment(0);

        setNavigation();
        setKlik();
    }

    void setKlik() {
        llBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilkanFragment(0);
                drawer.closeDrawers();
            }
        });

        llFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/alfirqotunnajiyahcom/"));
                startActivity(launchBrowser);
            }
        });

        llInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/al.firqotun.najiyah/"));
                startActivity(launchBrowser);
            }
        });

        llTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/alfirqotunnjyh"));
                startActivity(launchBrowser);
            }
        });

        llTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/al_firqotunnajiyah"));
                startActivity(launchBrowser);
            }
        });

        llYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCB2Q857sapIb-Xuj9r36EnA"));
                startActivity(launchBrowser);
            }
        });


    }

    void setNavigation() {
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_home, R.color.tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_feed_rss, R.color.tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_user, R.color.tab_3);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.colorPrimary));

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Enable the translation of the FloatingActionButton
        bottomNavigation.manageFloatingActionButtonBehavior(fab);

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        // Display color under navigation bar (API 21+)
        // Don't forget these lines in your style-v21
        // <item name="android:windowTranslucentNavigation">true</item>
        // <item name="android:fitsSystemWindows">true</item>
        bottomNavigation.setTranslucentNavigationEnabled(true);

        // Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        //bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        //bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

        // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);

        // Customize notification (title, background, typeface)
        //bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        // Add or remove notification for each item
        // bottomNavigation.setNotification("1", 3);
        // OR
        /*AHNotification notification = new AHNotification.Builder()
                .setText("1")
                .setBackgroundColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_back))
                .setTextColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_text))
                .build();
        bottomNavigation.setNotification(notification, 1);*/

        // Enable / disable item & set disable color
        /*bottomNavigation.enableItemAtPosition(2);
        bottomNavigation.disableItemAtPosition(2);
        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));*/

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                //tampilkanFragment(0);
                tampilkanFragment(position);
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                // Manage the new y position
            }
        });
    }

    void tampilkanFragment(int isiNa) {
        // Memulai transaksi
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // mengganti isi container dengan fragment baru
        if (isiNa == 0) {
            ft.replace(R.id.frameLayout, new FragmentBeranda());
        } else if (isiNa == 1) {
            ft.replace(R.id.frameLayout, new FragmentFeedPostingan());
        } else {
            ft.replace(R.id.frameLayout, new FragmentProfil());
        }
        // atau ft.add(R.id.your_placeholder, new FooFragment());
        // mulai melakukan hal di atas (jika belum di commit maka proses di atas belum dimulai)
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    /*@Override
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
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
