package com.iobrother.zchat.data.domain.request.passport;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.iobrother.zchat.data.bean.passport.SmsLoginReq;
import com.iobrother.zchat.data.bean.passport.SmsLoginRsp;
import com.iobrother.zchat.data.domain.request.BaseRequest;
import com.iobrother.zchat.data.repository.PassportRepository;
import com.kunminx.architecture.data.response.DataResult;
import com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData;
import com.kunminx.architecture.ui.callback.UnPeekLiveData;

public class SmsLoginRequest extends BaseRequest
    implements DefaultLifecycleObserver {

    private final UnPeekLiveData<DataResult<SmsLoginRsp>> loginResultLiveData = new UnPeekLiveData<>();
    public ProtectedUnPeekLiveData<DataResult<SmsLoginRsp>> getLoginResultLiveData() {
        return loginResultLiveData;
    }

    public void smsLogin(SmsLoginReq req) {
        PassportRepository.getInstance().smsLogin(req, loginResultLiveData::postValue);
    }

    private void cancelSmsLogin() {
        PassportRepository.getInstance().cancelSmsLogin();
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        cancelSmsLogin();
    }
}
