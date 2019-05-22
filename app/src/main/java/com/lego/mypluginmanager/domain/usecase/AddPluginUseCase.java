package com.lego.mypluginmanager.domain.usecase;

import com.lego.mypluginmanager.domain.repository.PluginRepository;

public class AddPluginUseCase {

    private PluginRepository repository;

    public AddPluginUseCase(PluginRepository repository) {
        this.repository = repository;
    }

    public void addPlugin(String pluginName){
        repository.add(pluginName);
    }
}
