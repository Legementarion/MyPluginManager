package com.lego.mypluginmanager.domain.usecase;

import com.lego.mypluginmanager.domain.repository.PluginRepository;

public class DeletePluginUseCase {

    private PluginRepository repository;

    public DeletePluginUseCase(PluginRepository repository) {
        this.repository = repository;
    }

    public void deletePluginByName(String pluginName) {
        repository.delete(pluginName);
    }

}
