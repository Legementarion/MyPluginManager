package com.lego.mypluginmanager.presentation;

import com.lego.mypluginmanager.base.BasePresenter;
import com.lego.mypluginmanager.base.BaseView;
import com.lego.mypluginmanager.domain.entity.PluginEntity;

import java.util.List;

public interface MainContract {

    interface Presenter extends BasePresenter {
        void getAllStoredPlugins();

        void addPlugin(String pluginName);

        void deletePlugin(String pluginName);
    }

    interface View extends BaseView {
        void showStoredPlugins(List<PluginEntity> pluginEntityList);

        void delete(String pluginName);

        void addPlugin(PluginEntity pluginName);

        boolean isStillExist(PluginEntity plugin);
    }

}
