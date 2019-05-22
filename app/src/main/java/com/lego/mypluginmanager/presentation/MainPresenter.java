package com.lego.mypluginmanager.presentation;

import com.lego.mypluginmanager.base.BasePresenterImpl;
import com.lego.mypluginmanager.domain.entity.PluginEntity;
import com.lego.mypluginmanager.domain.usecase.AddPluginUseCase;
import com.lego.mypluginmanager.domain.usecase.DeletePluginUseCase;
import com.lego.mypluginmanager.domain.usecase.GetAllPluginsUseCase;

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
                        view.showStoredPlugins(pluginEntityList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showToast("Error" + e.getMessage());
                    }
                });
    }

    @Override
    public void addPlugin(final String pluginName) {
        addPluginUseCase.addPlugin(pluginName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        view.addPlugin(new PluginEntity(pluginName));
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
