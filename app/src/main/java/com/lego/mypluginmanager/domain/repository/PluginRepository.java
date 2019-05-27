package com.lego.mypluginmanager.domain.repository;

import com.lego.mypluginmanager.domain.entity.PluginEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PluginRepository {

    Single<Boolean> add(PluginEntity plugin);

    Completable delete(String pluginName);

    Single<List<PluginEntity>> getAllPlugins();

}
