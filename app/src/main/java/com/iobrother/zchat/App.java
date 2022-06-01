package com.iobrother.zchat;

import com.iobrother.zchat.data.source.cache.AppCache;
import com.kunminx.architecture.BaseApplication;
import com.kunminx.architecture.utils.Utils;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        AppCache.init(this);
        AppCache.setLogin(false);
    }
}
