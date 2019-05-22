package com.lego.mypluginmanager.di.module.domain;

import com.lego.mypluginmanager.di.module.datasource.DataSourceModule;
import com.lego.mypluginmanager.domain.repository.PluginRepository;
import com.lego.mypluginmanager.domain.usecase.AddPluginUseCase;
import com.lego.mypluginmanager.domain.usecase.DeletePluginUseCase;
import com.lego.mypluginmanager.domain.usecase.GetAllPluginsUseCase;

import dagger.Module;
import dagger.Provides;

@Module(includes = DataSourceModule.class)
public class DomainModule {

    @Provides
    public GetAllPluginsUseCase provideGetAllPluginsUseCase(PluginRepository pluginRepository) {
        return new GetAllPluginsUseCase(pluginRepository);
    }

    @Provides
    public AddPluginUseCase provideAddPluginUseCase(PluginRepository pluginRepository) {
        return new AddPluginUseCase(pluginRepository);
    }

    @Provides
    public DeletePluginUseCase provideDeletePluginUseCase(PluginRepository pluginRepository) {
        return new DeletePluginUseCase(pluginRepository);
    }

}
