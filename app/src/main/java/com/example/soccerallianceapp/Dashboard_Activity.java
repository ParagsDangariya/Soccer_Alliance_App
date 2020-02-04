package com.example.soccerallianceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.soccer_alliance_project_test.R;
import com.google.android.material.navigation.NavigationView;

public class Dashboard_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public Toolbar DashboardToolbar;
    public DrawerLayout DashboarddrawerLayout;
    public NavigationView DashboardNavigationView;
    public NavController DashboardNavController;
    public TextView username;
    public ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setupNavigation();
    }

    public void setupNavigation(){

        DashboardToolbar = findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(DashboardToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DashboarddrawerLayout = findViewById(R.id.dashboard_drawer_layout);

        DashboardNavigationView = findViewById(R.id.dashboard_navigationview);
        username = DashboardNavigationView.getHeaderView(0).findViewById(R.id.nav_header_user_name);
        userImage = DashboardNavigationView.getHeaderView(0).findViewById(R.id.nav_header_userprofile_image);

        /*if(!PreferenceData.getUserName(this).equals("")){
            username.setText(PreferenceData.getUserName(this));
            DashboardNavigationView.getMenu().clear();
            DashboardNavigationView.inflateMenu(R.menu.drawer_menu);
        }
        else {
            DashboardNavigationView.getMenu().clear();
            DashboardNavigationView.inflateMenu(R.menu.guest_menu);
        }
        if(!PreferenceData.getUserprofile(this).equals("") && !PreferenceData.getUserprofile(this).equals("no image")){
            Glide.with(this)
                    .load("https://dinierecettes.online/images/"+PreferenceData.getUserprofile(this)+".jpg")
                    .centerCrop()
                    .into(userImage);
        }
*/
        DashboardNavigationView.getMenu().clear();
        DashboardNavigationView.inflateMenu(R.menu.guest_menu);
        DashboardNavController = Navigation.findNavController(this,R.id.dashboard_host_fragment);

        NavigationUI.setupActionBarWithNavController(this,DashboardNavController,DashboarddrawerLayout);
        NavigationUI.setupWithNavController(DashboardNavigationView,DashboardNavController);

        DashboardNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.dashboard_host_fragment),DashboarddrawerLayout);
    }

    @Override
    public void onBackPressed() {
        if (DashboarddrawerLayout.isDrawerOpen(GravityCompat.START)){

            DashboarddrawerLayout.closeDrawer(GravityCompat.START);

        }else {
            super.onBackPressed();
        }
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setCheckable(true);

        DashboarddrawerLayout.closeDrawers();

        return true;
    }

}
