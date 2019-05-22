package com.lego.mypluginmanager.data.datasource.local;

import com.lego.mypluginmanager.data.datasource.local.dabase.PluginTable;

import java.util.List;

import io.reactivex.Single;

public interface PluginLocalDataSource {

    void add(String pluginName);

    void delete(String pluginName);

    Single<List<PluginTable>> getAllPlugins();
}
