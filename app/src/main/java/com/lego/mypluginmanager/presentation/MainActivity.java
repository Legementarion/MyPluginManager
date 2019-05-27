package com.lego.mypluginmanager.presentation;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

import static com.lego.mypluginmanager.Constants.SCHEME;
import static com.lego.mypluginmanager.Constants.START_TASK_CODE;
import static com.lego.mypluginmanager.Constants.TASK_ACTION;
import static com.lego.mypluginmanager.Constants.TASK_CODE_EXTRA;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View,
        UninstallCallBack, InstallCallBack, MainAdapter.PluginClickListener {

    @Inject
    MainPresenter mainPresenter;
    private MainAdapter adapter = new MainAdapter();
    private UninstallPackageIntentReceiver uninstallPackageIntentReceiver = new UninstallPackageIntentReceiver();
    private InstallPluginReceiver installPackageIntentReceiver = new InstallPluginReceiver();
    private TextView tvEmptyState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((App) getApplicationContext()).appComponent.inject(this);
        super.onCreate(savedInstanceState);
        RecyclerView rvPlugins = findViewById(R.id.rvPlugins);
        tvEmptyState = findViewById(R.id.tvEmptyState);
        rvPlugins.setAdapter(adapter);
        rvPlugins.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter.setPluginClickListener(this);
        getPresenter().getAllStoredPlugins();
        setupCallBacks();
        setupReceivers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkExistPlugins();
    }

    private void checkExistPlugins() {
        PackageManager pm = getApplicationContext().getPackageManager();
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        List<ResolveInfo> resolveInfos = pm.queryIntentServices(new Intent(TASK_ACTION), PackageManager.GET_META_DATA);
        for (ResolveInfo resolveInfo : resolveInfos) {          //check existing plugins
            PluginEntity plugin = new PluginEntity(resolveInfo.serviceInfo.packageName, false);
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {  //check if service active now
                if (resolveInfo.serviceInfo.packageName.equals(service.service.getClassName())) {
                    plugin.setPluginEnable(true);
                }
            }
            getPresenter().addPlugin(plugin);     //Now when I know this methods, our database is redundant, but I want to leave it here, cuz i can :)
        }
    }

    private void setupCallBacks() {
        installPackageIntentReceiver.setupCallback(this);
        uninstallPackageIntentReceiver.setupCallback(this);
    }

    public void setupReceivers() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addDataScheme(SCHEME);
        registerReceiver(installPackageIntentReceiver, filter);

        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_FULLY_REMOVED);
        filter.addDataScheme(SCHEME);
        registerReceiver(uninstallPackageIntentReceiver, filter);
    }

    @Override
    public void addPlugin(PluginEntity plugin) {
        adapter.addPluginData(plugin);
        checkEmptyState();
    }

    @Override
    public void updatePlugin(PluginEntity plugin) {
        adapter.updatePlugin(plugin);
    }

    @Override
    public void onAppUninstalled(String packageName) {
        getPresenter().deletePlugin(packageName);
    }

    @Override
    public void onNewAppInstalled() {
        checkExistPlugins();
    }

    @Override
    public void delete(String pluginName) {
        adapter.checkingDelete(pluginName);
        checkEmptyState();
    }

    @Override
    public void onPluginSwitch(String pluginName, Boolean state) {
        Intent intent = new Intent();
        intent.setAction(TASK_ACTION);
        intent.setPackage(pluginName);
        if (state) {
            intent.putExtra(TASK_CODE_EXTRA, START_TASK_CODE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        } else {
            stopService(intent);
        }

    }

    @Override
    public boolean isStillExist(PluginEntity plugin) {
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(plugin.getPluginName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void showStoredPlugins(List<PluginEntity> pluginEntityList) {
        adapter.setPlugins(pluginEntityList);
        checkEmptyState();
    }

    private void checkEmptyState() {
        if (adapter.getItemCount() == 0) {
            tvEmptyState.setVisibility(View.VISIBLE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
        }
    }

    public void unregisterBroadcastReceiver() {
        this.unregisterReceiver(uninstallPackageIntentReceiver);
        this.unregisterReceiver(installPackageIntentReceiver);
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
