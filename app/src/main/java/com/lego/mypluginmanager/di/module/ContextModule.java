package com.lego.mypluginmanager.di.module;

import android.content.Context;

import com.lego.mypluginmanager.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    @Provides
    @Singleton
    public Context providesApplicationContext(App application) {
        return application.getApplicationContext();
    }

}
