package com.lego.mypluginmanager.di.module.datasource.db;

import android.content.Context;

import androidx.room.Room;

import com.lego.mypluginmanager.data.datasource.local.dabase.PluginDAO;
import com.lego.mypluginmanager.data.datasource.local.dabase.PluginDataBase;
import com.lego.mypluginmanager.di.module.ContextModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.lego.mypluginmanager.data.datasource.local.dabase.PluginDataBase.DB_NAME;

@Module(includes = ContextModule.class)
public class DataBaseModule {

    @Provides
    public PluginDAO provideDao(PluginDataBase dataBase) {
        return dataBase.pluginDao();
    }


    @Provides
    @Singleton
    public PluginDataBase provideDB(Context context) {
        return Room.databaseBuilder(context, PluginDataBase.class, DB_NAME).build();
    }

}
