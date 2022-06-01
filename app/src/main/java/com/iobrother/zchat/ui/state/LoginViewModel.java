package com.iobrother.zchat.ui.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.iobrother.zchat.data.domain.request.passport.SmsLoginRequest;

public class LoginViewModel extends ViewModel {

    public final ObservableField<String> mobile = new ObservableField<>();
    public final ObservableField<String> code = new ObservableField<>();
    public final SmsLoginRequest smsLoginRequest = new SmsLoginRequest();
}
