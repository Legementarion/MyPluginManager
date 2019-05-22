package com.lego.mypluginmanager.base;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenterImpl<V extends BaseView> implements BasePresenter {

    protected V view = null;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    @SuppressWarnings("UNCHECKED_CAST")
    public void attachView(BaseView view) {
        this.view = (V) view;
    }

    @Override
    public void detachView() {
        view = null;
        compositeDisposable.dispose();
    }

    @Override
    public void viewShown() {
    }

    @Override
    public void viewHidden() {
        compositeDisposable.clear();
    }
}
