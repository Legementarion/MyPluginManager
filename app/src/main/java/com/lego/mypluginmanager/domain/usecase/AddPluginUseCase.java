package com.lego.mypluginmanager.domain.usecase;

import com.lego.mypluginmanager.domain.entity.PluginEntity;
import com.lego.mypluginmanager.domain.repository.PluginRepository;

import io.reactivex.Single;

public class AddPluginUseCase {

    private PluginRepository repository;

    public AddPluginUseCase(PluginRepository repository) {
        this.repository = repository;
    }

    public Single<Boolean> addPlugin(PluginEntity plugin){
       return repository.add(plugin);
    }
}
