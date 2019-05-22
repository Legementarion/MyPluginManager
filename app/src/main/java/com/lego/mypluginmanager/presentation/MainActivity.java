package com.lego.mypluginmanager.presentation;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lego.mypluginmanager.App;
import com.lego.mypluginmanager.receivers.InstallCallBack;
import com.lego.mypluginmanager.receivers.InstallPluginReceiver;
import com.lego.mypluginmanager.receivers.UninstallCallBack;
import com.lego.mypluginmanager.receivers.UninstallPackageIntentReceiver;
import com.lego.mypluginmanager.domain.entity.PluginEntity;
import com.lego.mypluginmanager.R;
import com.lego.mypluginmanager.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import static com.lego.mypluginmanager.Constants.REGISTRATION_ACTION;
import static com.lego.mypluginmanager.Constants.SCHEME;
import static com.lego.mypluginmanager.Constants.START_TASK_CODE;
import static com.lego.mypluginmanager.Constants.STOP_TASK_CODE;
import static com.lego.mypluginmanager.Constants.TASK_ACTION;
import static com.lego.mypluginmanager.Constants.TASK_CODE_EXTRA;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, InstallCallBack, UninstallCallBack, MainAdapter.PluginClickListener {

    @Inject
    MainPresenter mainPresenter;
    private MainAdapter adapter = new MainAdapter();
    private InstallPluginReceiver installBroadCastReceiver = new InstallPluginReceiver();
    private UninstallPackageIntentReceiver uninstallPackageIntentReceiver = new UninstallPackageIntentReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((App) getApplicationContext()).appComponent.inject(this);
        super.onCreate(savedInstanceState);
        RecyclerView rvPlugins = findViewById(R.id.rvPlugins);
        rvPlugins.setAdapter(adapter);
        rvPlugins.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter.setPluginClickListener(this);
        getPresenter().getAllStoredPlugins();
        setupCallBacks();
        setupReceivers();
    }

    private void setupCallBacks() {
        installBroadCastReceiver.setupCallback(this);
        uninstallPackageIntentReceiver.setupCallback(this);
    }

    public void setupReceivers() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(REGISTRATION_ACTION);
        registerReceiver(installBroadCastReceiver, filter);

        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_FULLY_REMOVED);
        filter.addDataScheme(SCHEME);
        registerReceiver(uninstallPackageIntentReceiver, filter);
    }

    @Override
    public void onNewAppInstalled(String packageName) {
        getPresenter().addPlugin(packageName);
    }

    @Override
    public void onAppUninstalled(String packageName) {
        getPresenter().deletePlugin(packageName);
        adapter.checkingDelete(packageName);
    }

    @Override
    public void onPluginSwitch(String pluginName, Boolean state) {
        Intent intent = new Intent(pluginName + "_" + TASK_ACTION);

        if (state) {
            intent.putExtra(TASK_CODE_EXTRA, START_TASK_CODE);
        } else {
            intent.putExtra(TASK_CODE_EXTRA, STOP_TASK_CODE);
        }
        sendBroadcast(intent);
    }

    @Override
    public void showStoredPlugins(List<PluginEntity> pluginEntityList) {
        adapter.setPlugins(pluginEntityList);
    }

    // Отменяем регистрацию
    public void unregisterBroadcastReceiver() {
        this.unregisterReceiver(installBroadCastReceiver);
        this.unregisterReceiver(uninstallPackageIntentReceiver);
    }

    @Override
    protected void onDestroy() {
        unregisterBroadcastReceiver();
        super.onDestroy();
    }

    @Override
    public MainContract.Presenter getPresenter() {
        return mainPresenter;
    }

    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }

}
