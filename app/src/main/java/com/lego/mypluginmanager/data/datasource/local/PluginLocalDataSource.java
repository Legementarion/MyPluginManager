package com.lego.mypluginmanager.data.datasource.local;

import com.lego.mypluginmanager.data.datasource.local.dabase.PluginTable;
import com.lego.mypluginmanager.domain.entity.PluginEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PluginLocalDataSource {

    Single<Boolean> add(PluginEntity plugin);

    Completable delete(String pluginName);

    Single<List<PluginTable>> getAllPlugins();
}
