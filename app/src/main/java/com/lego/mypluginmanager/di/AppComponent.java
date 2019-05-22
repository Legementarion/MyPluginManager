package com.lego.mypluginmanager.di;

import android.content.Context;

import com.lego.mypluginmanager.App;
import com.lego.mypluginmanager.di.module.ContextModule;
import com.lego.mypluginmanager.di.module.datasource.DataSourceModule;
import com.lego.mypluginmanager.di.module.datasource.db.DataBaseModule;
import com.lego.mypluginmanager.di.module.domain.DomainModule;
import com.lego.mypluginmanager.di.module.presentation.MainModule;
import com.lego.mypluginmanager.presentation.MainActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {MainModule.class,
        DomainModule.class,
        DataSourceModule.class,
        DataBaseModule.class,
        ContextModule.class})
public interface AppComponent {

    Context getContext();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(App application);

        AppComponent build();
    }


    void inject(App app);

    void inject(MainActivity activity);

}
