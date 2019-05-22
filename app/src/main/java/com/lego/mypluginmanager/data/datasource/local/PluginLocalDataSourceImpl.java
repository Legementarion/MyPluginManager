package com.lego.mypluginmanager.data.datasource.local;

import com.lego.mypluginmanager.data.datasource.local.dabase.PluginDAO;
import com.lego.mypluginmanager.data.datasource.local.dabase.PluginTable;

import java.util.List;

import io.reactivex.Single;

public class PluginLocalDataSourceImpl implements PluginLocalDataSource {

    private PluginDAO pluginDAO;

    public PluginLocalDataSourceImpl(PluginDAO dataSource) {
        this.pluginDAO = dataSource;
    }

    @Override
    public void add(String pluginName) {
        PluginTable plugin = new PluginTable();
        plugin.name = pluginName;
        pluginDAO.addPlugin(plugin);
    }

    @Override
    public void delete(String pluginName) {
        pluginDAO.deletePlugin(pluginName);
    }

    @Override
    public Single<List<PluginTable>> getAllPlugins() {
        return pluginDAO.getAllPlugins();
    }
}
