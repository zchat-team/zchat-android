package com.iobrother.zchat;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.iobrother.zchat.data.source.cache.AppCache;
import com.iobrother.zchat.ui.state.MainActivityViewModel;
import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;

public class MainActivity extends BaseActivity {

    private MainActivityViewModel mState;

    @Override
    protected void initViewModel() {
        mState = getActivityScopeViewModel(MainActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, BR.vm, mState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
                findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener((controller, destination, bundle) -> {
            if (destination.getId() == R.id.mainFragment) {
                if (!mState.done.get()) {
                    controller.navigate(R.id.welcomeFragment);
                    return;
                }

                if (!AppCache.isLogin()) {
                    controller.navigate(R.id.loginFragment);
                }else{
                    ZimHelper.getInstance().init(getApplicationContext());
                }
            }
        });
    }
}