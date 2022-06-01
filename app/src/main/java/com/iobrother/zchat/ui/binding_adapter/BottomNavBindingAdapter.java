package com.iobrother.zchat.ui.binding_adapter;

import androidx.databinding.BindingAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.iobrother.zchat.R;

public class BottomNavBindingAdapter {
    @BindingAdapter(value = {"initBottomNav"}, requireAll = false)
    public static void initBottomNav(BottomNavigationView bottomNavigationView, Boolean initBottomNav) {
        NavController navController = Navigation.findNavController(
                bottomNavigationView.getRootView().findViewById(R.id.fragmentContainerView)
        );
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}
