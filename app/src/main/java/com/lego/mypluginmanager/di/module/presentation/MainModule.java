package com.lego.mypluginmanager.di.module.presentation;

import com.lego.mypluginmanager.di.module.domain.DomainModule;
import com.lego.mypluginmanager.domain.usecase.AddPluginUseCase;
import com.lego.mypluginmanager.domain.usecase.DeletePluginUseCase;
import com.lego.mypluginmanager.domain.usecase.GetAllPluginsUseCase;
import com.lego.mypluginmanager.presentation.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = DomainModule.class)
public class MainModule {

    @Provides
    public MainPresenter provideMainPresenter(GetAllPluginsUseCase getAllPluginsUseCase,
                                              AddPluginUseCase addPluginUseCase,
                                              DeletePluginUseCase deletePluginUseCase) {
        return new MainPresenter(addPluginUseCase, deletePluginUseCase, getAllPluginsUseCase);
    }
}
