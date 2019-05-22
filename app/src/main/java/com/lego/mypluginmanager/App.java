package com.lego.mypluginmanager;

import android.app.Application;

import com.lego.mypluginmanager.di.AppComponent;
import com.lego.mypluginmanager.di.DaggerAppComponent;

public class App extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();

        appComponent.inject(this);
    }

}
