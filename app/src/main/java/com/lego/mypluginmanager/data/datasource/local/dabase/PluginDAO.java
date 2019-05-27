package com.lego.mypluginmanager.data.datasource.local.dabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PluginDAO {

    @Query("Select * from Plugins")
    Single<List<PluginTable>> getAllPlugins();

    @Query("Select * from Plugins Where pluginName == :pluginName")
    PluginTable getPluginByName(String pluginName);

    @Insert
    void addPlugin(PluginTable plugin);

    @Update
    void updatePlugin(PluginTable plugin);

    @Query("DELETE FROM plugins WHERE pluginName = :pluginName")
    void deletePlugin(String pluginName);

}
