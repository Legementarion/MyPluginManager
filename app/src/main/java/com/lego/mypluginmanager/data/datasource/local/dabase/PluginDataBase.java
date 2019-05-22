package com.lego.mypluginmanager.data.datasource.local.dabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = PluginTable.class, version = 1, exportSchema = false)
public abstract class PluginDataBase extends RoomDatabase {
    public static final String DB_NAME = "plugin_db.db";

    public abstract PluginDAO pluginDao();
}
