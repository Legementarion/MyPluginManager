package com.lego.mypluginmanager.base;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lego.mypluginmanager.App;

import java.util.Objects;

public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        getPresenter().attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().viewShown();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getPresenter().viewHidden();
    }

    @Override
    protected void onDestroy() {
        getPresenter().detachView();
        super.onDestroy();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideKeyboard() {
        ((InputMethodManager) getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
    }

    public abstract Presenter getPresenter();

    public abstract int getLayoutResourceId();

}
