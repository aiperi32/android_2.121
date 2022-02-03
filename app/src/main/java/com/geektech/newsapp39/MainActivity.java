package com.geektech.newsapp39;

import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_profail).build();
        navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if (!App.getPrefs().isBoardShown()){
            navController.navigate(R.id.boardFragment);
            App.getPrefs().saveBoardStarts();
        }

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            List<Integer> tabFragments = new ArrayList<>();
            tabFragments.add(R.id.navigation_home);
            tabFragments.add(R.id.navigation_dashboard);
            tabFragments.add(R.id.navigation_notifications);
            tabFragments.add(R.id.navigation_profail);

            if (tabFragments.contains(destination.getId())) {
                navView.setVisibility(View.VISIBLE);
            } else {
                navView.setVisibility(View.GONE);
            }

            // board/fragment toolbar: hide\show
            if (destination.getId() == R.id.boardFragment) {
                getSupportActionBar().hide();
            } else {
                getSupportActionBar().show();
            }


        });
    }

    //
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}