package com.titdevelopercommunity.titsports;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fm = getSupportFragmentManager();
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            loadFragment(new Home(), 0);
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_highlight) {
            loadFragment(new SportsHighlight(), 1);
        } else if (item.getItemId() == R.id.nav_achievement) {
            loadFragment(new AddAchievements(), 1);
        } else if (item.getItemId() == R.id.nav_uplaod) {
            loadFragment(new UplaodDocuments(), 1);
        } else if (item.getItemId() == R.id.nav_setting) {
            loadFragment(new Setting(), 1);
        } else if (item.getItemId() == R.id.nav_about_developer) {
            loadFragment(new AboutDeveloper(), 1);
        } else if (item.getItemId() == R.id.nav_logout) {
            loadFragment(new Logout(), 1); }
        else if(item.getItemId()==R.id.nav_whatsapp){
            loadFragment(new WhatsappGroup(),1);
        }
        else {
                loadFragment(new Home(), 0);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("CommitTransaction")
    public void loadFragment(Fragment fragment, int flag) {

        FragmentTransaction ft = fm.beginTransaction();
        if (flag == 0) {
            ft.add(R.id.frame_container, fragment);
            fm.popBackStack("root_fragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            ft.replace(R.id.frame_container, fragment);
            ft.addToBackStack(null);
        }

        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen((GravityCompat.START))) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            MainActivity.super.onBackPressed();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

