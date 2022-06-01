package com.iobrother.zchat.ui.page.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.iobrother.zchat.BR;
import com.iobrother.zchat.R;
import com.iobrother.zchat.data.bean.passport.SmsLoginReq;
import com.iobrother.zchat.data.bean.passport.SmsLoginRsp;
import com.iobrother.zchat.data.bean.user.UserInfo;
import com.iobrother.zchat.data.source.cache.AppCache;
import com.iobrother.zchat.ui.state.LoginViewModel;
import com.kunminx.architecture.ui.page.BaseFragment;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.kunminx.architecture.utils.ToastUtils;

public class LoginFragment extends BaseFragment {

    private LoginViewModel mState;

    @Override
    protected void initViewModel() {
        mState = getFragmentScopeViewModel(LoginViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_login, BR.vm, mState)
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(mState.smsLoginRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO: 删除以下两行代码，写在这里仅为了方便测试
        mState.mobile.set("13705931463");
        mState.code.set("123456");

        mState.smsLoginRequest.getLoginResultLiveData().observe(this, result -> {
            if (!result.getResponseStatus().isSuccess()) {
                ToastUtils.showLongToast(getApplicationContext(), "网络状态不佳，请重试");

                return;
            }

            SmsLoginRsp rsp = result.getResult();
            Log.d("TEST", "login succ: "+rsp.token.accessToken);

            UserInfo info = new UserInfo();
            info.uid = rsp.uid;
            info.token = rsp.token;
            AppCache.setUserInfo(info);
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigateUp();
        });
    }

    public class ClickProxy {
        public void sendSms() {

        }

        public void login() {
            SmsLoginReq req = new SmsLoginReq(mState.mobile.get(), mState.code.get());
            mState.smsLoginRequest.smsLogin(req);
        }
    }
}