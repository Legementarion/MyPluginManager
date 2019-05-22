package com.lego.mypluginmanager.base;

public interface BasePresenter {

    void attachView(BaseView view);

    void detachView();

    void viewShown();

    void viewHidden();
}
