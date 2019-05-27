package com.lego.mypluginmanager.presentation;

import com.lego.mypluginmanager.base.BasePresenterImpl;
import com.lego.mypluginmanager.domain.entity.PluginEntity;
import com.lego.mypluginmanager.domain.usecase.AddPluginUseCase;
import com.lego.mypluginmanager.domain.usecase.DeletePluginUseCase;
import com.lego.mypluginmanager.domain.usecase.GetAllPluginsUseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    private AddPluginUseCase addPluginUseCase;
    private DeletePluginUseCase deletePluginUseCase;
    private GetAllPluginsUseCase getAllPluginsUseCase;

    public MainPresenter(AddPluginUseCase addPluginUseCase,
                         DeletePluginUseCase deletePluginUseCase,
                         GetAllPluginsUseCase getAllPluginsUseCase) {
        this.addPluginUseCase = addPluginUseCase;
        this.deletePluginUseCase = deletePluginUseCase;
        this.getAllPluginsUseCase = getAllPluginsUseCase;
    }

    @Override
    public void getAllStoredPlugins() {
        getAllPluginsUseCase.getAllPlugins()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<PluginEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<PluginEntity> pluginEntityList) {
                        ArrayList<PluginEntity> result = new ArrayList<>();
                        for (PluginEntity plugin : pluginEntityList) {
                            if (view.isStillExist(plugin)) {
                                result.add(plugin);
                            } else {
                                deletePlugin(plugin.getPluginName());
                            }
                        }
                        view.showStoredPlugins(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showToast("Error" + e.getMessage());
                    }
                });
    }

    @Override
    public void addPlugin(final PluginEntity plugin) {
        addPluginUseCase.addPlugin(plugin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Boolean isSuccess) {
                        if (isSuccess) {
                            view.addPlugin(plugin);
                        } else {
                            view.updatePlugin(plugin);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showToast("Error" + e.getMessage());
                    }
                });
    }

    @Override
    public void deletePlugin(final String pluginName) {
        deletePluginUseCase.deletePluginByName(pluginName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        view.delete(pluginName);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showToast("Error" + e.getMessage());
                    }
                });
    }

}
