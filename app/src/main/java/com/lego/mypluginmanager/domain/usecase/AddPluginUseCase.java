package com.lego.mypluginmanager.domain.usecase;

import com.lego.mypluginmanager.domain.repository.PluginRepository;

import io.reactivex.Completable;

public class AddPluginUseCase {

    private PluginRepository repository;

    public AddPluginUseCase(PluginRepository repository) {
        this.repository = repository;
    }

    public Completable addPlugin(String pluginName){
       return repository.add(pluginName);
    }
}
