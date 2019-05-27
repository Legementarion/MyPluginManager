package com.lego.mypluginmanager.presentation;

import com.lego.mypluginmanager.base.BasePresenter;
import com.lego.mypluginmanager.base.BaseView;
import com.lego.mypluginmanager.domain.entity.PluginEntity;

import java.util.List;

public interface MainContract {

    interface Presenter extends BasePresenter {
        void getAllStoredPlugins();

        void addPlugin(PluginEntity plugin);

        void deletePlugin(String pluginName);

    }

    interface View extends BaseView {

        void showStoredPlugins(List<PluginEntity> pluginEntityList);

        void delete(String pluginName);

        void addPlugin(PluginEntity plugin);

        void updatePlugin(PluginEntity plugin);

        boolean isStillExist(PluginEntity plugin);
    }

}
