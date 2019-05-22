package com.lego.mypluginmanager.data.datasource.mapper;

import com.lego.mypluginmanager.data.datasource.local.dabase.PluginTable;
import com.lego.mypluginmanager.domain.entity.PluginEntity;

public class LocalMapper {

    public static PluginEntity map(PluginTable pluginTable) {
        return new PluginEntity(pluginTable.name);
    }
}
