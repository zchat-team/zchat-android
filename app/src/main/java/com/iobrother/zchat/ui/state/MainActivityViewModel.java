package com.iobrother.zchat.ui.state;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    public final ObservableBoolean done = new ObservableBoolean(false);
}
