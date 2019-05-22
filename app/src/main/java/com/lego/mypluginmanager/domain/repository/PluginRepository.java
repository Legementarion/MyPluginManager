package com.lego.mypluginmanager.domain.repository;

import com.lego.mypluginmanager.domain.entity.PluginEntity;

import java.util.List;

import io.reactivex.Single;

public interface PluginRepository {

    void add(String pluginName);

    void delete(String pluginName);

    Single<List<PluginEntity>> getAllPlugins();

}
