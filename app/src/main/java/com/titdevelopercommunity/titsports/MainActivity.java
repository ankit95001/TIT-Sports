package com.titdevelopercommunity.titsports;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_UPDATE_CODE = 1000;
    private AppUpdateManager appUpdateManager;
    private static final String TAG = "AppUpdate";

    private ActivityResultLauncher<IntentSenderRequest> updateActivityResultLauncher;
    private DrawerLayout drawerLayout;
    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup Drawer Layout and Navigation View
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Setup ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open_nav,
                R.string.close_nav
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Load Home fragment by default
        if (savedInstanceState == null) {
            loadFragment(new Home(), 0);
            navigationView.setCheckedItem(R.id.nav_home);
        }

        // Initialize AppUpdateManager
        appUpdateManager = AppUpdateManagerFactory.create(this);

        // Initialize ActivityResultLauncher
        initializeUpdateActivityResultLauncher();

        // Check for update
        checkForAppUpdate();
    }

    /**
     * Initializes the ActivityResultLauncher to handle the update flow result.
     */
    private void initializeUpdateActivityResultLauncher() {
        updateActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartIntentSenderForResult(),
                result -> {
                    if (result.getResultCode() != RESULT_OK) {
                        Log.e(TAG, "Update flow failed! Result code: " + result.getResultCode());
                        Toast.makeText(this, "Update failed or canceled", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i(TAG, "Update flow completed successfully");
                        Toast.makeText(this, "App updated successfully", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void checkForAppUpdate() {
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                try {
                    // Start the update
                    appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo,
                            this,
                            AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build(),
                            REQUEST_UPDATE_CODE);
                } catch (IntentSender.SendIntentException e) {
                    Toast.makeText(this, "Update initiation failed", Toast.LENGTH_SHORT).show();
                    Log.e("AppUpdate", "Update initiation failed", e);
                }
            }
        }).addOnFailureListener(e -> Log.e("AppUpdate", "Failed to check for updates", e));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_UPDATE_CODE) {
            if (resultCode != RESULT_OK) {
                Toast.makeText(this, "Update failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Handles navigation item selections.
     */
    @Override
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

    @Override
    protected void onResume() {
        super.onResume();
    }
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
