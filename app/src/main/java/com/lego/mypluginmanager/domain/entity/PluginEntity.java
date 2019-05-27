package com.lego.mypluginmanager.domain.entity;

public class PluginEntity {

    private String pluginName;
    private boolean isPluginEnable;

    public PluginEntity(String pluginName, boolean isEnable) {
        this.pluginName = pluginName;
        isPluginEnable = isEnable;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public boolean isPluginEnable() {
        return isPluginEnable;
    }

    public void setPluginEnable(boolean pluginEnable) {
        isPluginEnable = pluginEnable;
    }
}
