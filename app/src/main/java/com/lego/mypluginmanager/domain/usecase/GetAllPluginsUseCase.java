package com.lego.mypluginmanager.domain.usecase;

import com.lego.mypluginmanager.domain.entity.PluginEntity;
import com.lego.mypluginmanager.domain.repository.PluginRepository;

import java.util.List;

import io.reactivex.Single;

public class GetAllPluginsUseCase {

    private PluginRepository repository;

    public GetAllPluginsUseCase(PluginRepository repository) {
        this.repository = repository;
    }

    public Single<List<PluginEntity>> getAllPlugins() {
        return repository.getAllPlugins();
    }

}
