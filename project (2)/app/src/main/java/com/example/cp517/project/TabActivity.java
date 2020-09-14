package com.example.cp517.project;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class TabActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private  DrawerLayout drawer;
    Fragment fragment;
    long lastPressed;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                   switchFragment(fragment);

                    return true;
                case R.id.navigation_dashboard:
                    fragment = new FriendsFragment();
                    switchFragment(fragment);

                    return true;
                case R.id.navigation_notifications:
                    fragment = new ProfileFragment();
                    switchFragment(fragment);
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.content, fragment);

        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    public void switchFragment(Fragment fragment){

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.content, fragment);

// Commit the transaction
        transaction.commit();
    }


    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - lastPressed < 1500){
            finish();
        }
        lastPressed = System.currentTimeMillis();

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.nav_home:
                fragment = new HomeFragment();
                switchFragment(fragment);
                break;
            case  R.id.nav_friend:
                fragment = new FriendsFragment();
                switchFragment(fragment);
                break;
            case  R.id.nav_profile:
                fragment = new ProfileFragment();
                switchFragment(fragment);
                break;
            case  R.id.nav_end:
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(TabActivity.this);

                alert_confirm.setMessage("앱을 종료 하시겠습니까?")
                        .setCancelable(false).setPositiveButton("아니오",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        })
                        .setNegativeButton("네",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });

                AlertDialog alert = alert_confirm.create();
                alert.show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}