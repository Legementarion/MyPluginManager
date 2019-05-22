package com.lego.mypluginmanager.data.datasource.local;

import com.lego.mypluginmanager.data.datasource.local.dabase.PluginTable;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PluginLocalDataSource {

    Completable add(String pluginName);

    Completable delete(String pluginName);

    Single<List<PluginTable>> getAllPlugins();
}
