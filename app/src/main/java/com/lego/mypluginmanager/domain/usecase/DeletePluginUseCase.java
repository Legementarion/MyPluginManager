package com.lego.mypluginmanager.domain.usecase;

import com.lego.mypluginmanager.domain.repository.PluginRepository;

import io.reactivex.Completable;

public class DeletePluginUseCase {

    private PluginRepository repository;

    public DeletePluginUseCase(PluginRepository repository) {
        this.repository = repository;
    }

    public Completable deletePluginByName(String pluginName) {
        return repository.delete(pluginName);
    }

}
