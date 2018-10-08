package com.github.playground.base;

import android.support.v7.app.AppCompatActivity;

import com.github.playground.PlaygroundApp;
import com.github.playground.dagger2.BaseActivityViewModelComponent;
import com.github.playground.dagger2.DaggerBaseActivityViewModelComponent;

public class BaseActivityViewModel {

    public BaseActivityViewModelComponent component;
    private AppCompatActivity activity;

    public BaseActivityViewModel(AppCompatActivity activity) {
        this.activity = activity;
        component = DaggerBaseActivityViewModelComponent
                .builder()
                .playgroundAppComponent(PlaygroundApp.get(activity).getComponent())
                .build();
        component.injectBaseActivityViewModel(this);
    }

}
