package com.lego.mypluginmanager.data.repository;

import com.lego.mypluginmanager.data.datasource.local.PluginLocalDataSource;
import com.lego.mypluginmanager.data.datasource.local.dabase.PluginTable;
import com.lego.mypluginmanager.data.datasource.mapper.LocalMapper;
import com.lego.mypluginmanager.domain.entity.PluginEntity;
import com.lego.mypluginmanager.domain.repository.PluginRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class PluginRepositoryImpl implements PluginRepository {

    private PluginLocalDataSource localDataSource;

    public PluginRepositoryImpl(PluginLocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    @Override
    public Single<List<PluginEntity>> getAllPlugins() {
        return localDataSource.getAllPlugins().map(
                new Function<List<PluginTable>, List<PluginEntity>>() {
                    @Override
                    public List<PluginEntity> apply(List<PluginTable> pluginTables) {
                        ArrayList<PluginEntity> result = new ArrayList<>();
                        for (PluginTable pluginTable : pluginTables) {
                            result.add(LocalMapper.map(pluginTable));
                        }
                        return result;
                    }
                });
    }

    @Override
    public Single<Boolean> add(PluginEntity plugin) {
       return localDataSource.add(plugin);
    }

    @Override
    public Completable delete(String pluginName) {
       return localDataSource.delete(pluginName);
    }
}
