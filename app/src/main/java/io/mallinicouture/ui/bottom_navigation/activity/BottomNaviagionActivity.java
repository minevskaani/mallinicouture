package io.mallinicouture.ui.bottom_navigation.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import io.mallinicouture.R;
import io.mallinicouture.base.BaseActivity;
import io.mallinicouture.databinding.ActivityBottomNavigationBinding;

public class BottomNaviagionActivity extends BaseActivity {
    private static final String TAG = "BottomNaviagionActivity";
    private ActivityBottomNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBottomNavigationBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        BottomNavigationView navView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // Removed because theme is NoActionBar
        // AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        //         R.id.navigation_home, R.id.navigation_favourite, R.id.navigation_shopping_basket)
        //         .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
