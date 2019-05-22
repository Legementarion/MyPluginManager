package com.lego.mypluginmanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.lego.mypluginmanager.Constants.PLUGIN_REGISTRATION_PACKAGE_NAME_EXTRA;

public class InstallPluginReceiver extends BroadcastReceiver {

    private InstallCallBack callBack;

    @Override
    public void onReceive(Context context, final Intent intent) {
        String packageName = intent.getStringExtra(PLUGIN_REGISTRATION_PACKAGE_NAME_EXTRA);
        if (packageName != null) {
            callBack.onNewAppInstalled(packageName);
        }
    }

    public void setupCallback(InstallCallBack callBack) {
        this.callBack = callBack;
    }

}
