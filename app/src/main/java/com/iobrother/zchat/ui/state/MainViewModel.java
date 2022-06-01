package com.iobrother.zchat.ui.state;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public final ObservableBoolean initBottomNav = new ObservableBoolean(true);
}
