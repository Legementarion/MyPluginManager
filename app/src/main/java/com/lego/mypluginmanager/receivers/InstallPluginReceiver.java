package com.lego.mypluginmanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class InstallPluginReceiver extends BroadcastReceiver {

    private InstallCallBack callBack;

    @Override
    public void onReceive(Context context, final Intent intent) {
        if (intent.getData() != null) {
            callBack.onNewAppInstalled();
        }
    }

    public void setupCallback(InstallCallBack callBack) {
        this.callBack = callBack;
    }

}
