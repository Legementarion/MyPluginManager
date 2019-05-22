package com.lego.mypluginmanager.data.datasource.local.dabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Plugins")
public class PluginTable {
    @PrimaryKey
    @ColumnInfo(name = "pluginName")
    @NotNull
    public String name = "";
}
