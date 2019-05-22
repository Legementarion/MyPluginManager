package com.lego.mypluginmanager.di.module.datasource;

import com.lego.mypluginmanager.data.datasource.local.PluginLocalDataSource;
import com.lego.mypluginmanager.data.datasource.local.PluginLocalDataSourceImpl;
import com.lego.mypluginmanager.data.datasource.local.dabase.PluginDAO;
import com.lego.mypluginmanager.data.repository.PluginRepositoryImpl;
import com.lego.mypluginmanager.di.module.datasource.db.DataBaseModule;
import com.lego.mypluginmanager.domain.repository.PluginRepository;

import dagger.Module;
import dagger.Provides;

@Module(includes = DataBaseModule.class)
public class DataSourceModule {

    @Provides
    public PluginRepository providePluginRepositorye(PluginLocalDataSource pluginLocalDataSource) {
        return new PluginRepositoryImpl(pluginLocalDataSource);
    }

    @Provides
    public PluginLocalDataSource providePluginLocalDataSource(PluginDAO dao) {
        return new PluginLocalDataSourceImpl(dao);
    }

}
