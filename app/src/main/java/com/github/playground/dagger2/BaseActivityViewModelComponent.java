package com.github.playground.dagger2;

import com.github.playground.base.BaseActivityViewModel;
import com.github.playground.dagger2.PlaygroundAppComponent;
import com.github.playground.multipart.viewModels.FileUploadActivityViewModel;

import dagger.Component;

@ActivityScope
@Component(dependencies = PlaygroundAppComponent.class)
public interface BaseActivityViewModelComponent {

    void injectBaseActivityViewModel(BaseActivityViewModel baseActivityViewModel);

    void injectFileUploadActivityViewModel(FileUploadActivityViewModel fileUploadActivityViewModel);
}
