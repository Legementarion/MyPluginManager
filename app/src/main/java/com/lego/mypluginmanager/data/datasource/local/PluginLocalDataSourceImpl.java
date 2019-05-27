package com.lego.mypluginmanager.data.datasource.local;

import com.lego.mypluginmanager.data.datasource.local.dabase.PluginDAO;
import com.lego.mypluginmanager.data.datasource.local.dabase.PluginTable;
import com.lego.mypluginmanager.domain.entity.PluginEntity;

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
    public Single<Boolean> add(final PluginEntity plugin) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                PluginTable pluginTable = pluginDAO.getPluginByName(plugin.getPluginName());
                if (pluginTable == null) {
                    PluginTable newPlugin = new PluginTable();
                    newPlugin.name = plugin.getPluginName();
                    pluginDAO.addPlugin(newPlugin);
                    return true;
                } else {
                    pluginTable.isEnable = plugin.isPluginEnable();
                    pluginDAO.updatePlugin(pluginTable);
                    return false;
                }
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
