package com.lego.mypluginmanager.data.datasource.local.dabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PluginDAO {

    @Query("Select * from Plugins")
    Single<List<PluginTable>> getAllPlugins();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPlugin(PluginTable plugin);

    @Query("DELETE FROM plugins WHERE pluginName = :pluginName")
    void deletePlugin(String pluginName);

}
