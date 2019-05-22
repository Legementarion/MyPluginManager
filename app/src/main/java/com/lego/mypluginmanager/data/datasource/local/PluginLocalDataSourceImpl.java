package com.lego.mypluginmanager.data.datasource.local;

import com.lego.mypluginmanager.data.datasource.local.dabase.PluginDAO;
import com.lego.mypluginmanager.data.datasource.local.dabase.PluginTable;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Single;

public class PluginLocalDataSourceImpl implements PluginLocalDataSource {

    private PluginDAO pluginDAO;

    public PluginLocalDataSourceImpl(PluginDAO dataSource) {
        this.pluginDAO = dataSource;
    }

    @Override
    public Completable add(final String pluginName) {
        return Completable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() {
                PluginTable plugin = new PluginTable();
                plugin.name = pluginName;
                pluginDAO.addPlugin(plugin);
                return null;
            }
        });
    }

    @Override
    public Completable delete(final String pluginName) {
        return Completable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() {
                pluginDAO.deletePlugin(pluginName);
                return null;
            }
        });
    }

    @Override
    public Single<List<PluginTable>> getAllPlugins() {
        return pluginDAO.getAllPlugins();
    }
}
