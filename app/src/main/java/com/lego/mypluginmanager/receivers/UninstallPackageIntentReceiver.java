package com.lego.mypluginmanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UninstallPackageIntentReceiver extends BroadcastReceiver {

    private UninstallCallBack callBack;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getData() != null) {
            callBack.onAppUninstalled(intent.getDataString().replace("package:", ""));
        }
    }

    public void setupCallback(UninstallCallBack callBack) {
        this.callBack = callBack;
    }

}
