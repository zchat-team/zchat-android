package com.iobrother.zchat.ui.page.login;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.iobrother.zchat.BR;
import com.iobrother.zchat.R;
import com.iobrother.zchat.ui.state.MainActivityViewModel;
import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;

public class WelcomeFragment extends BaseFragment {

    private MainActivityViewModel mState;

    @Override
    protected void initViewModel() {
        mState = getActivityScopeViewModel(MainActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_welcome, BR.vm, mState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mState.done.set(true);
                nav().navigateUp();
            }
        }, 1000);
    }
}