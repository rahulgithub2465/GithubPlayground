package com.github.playground.dagger2;

import com.github.playground.PlaygroundApp;
import com.github.playground.multipart.apiImplementations.UploadFileApiImplementation;

import dagger.Component;

@Component(modules = {PlaygroundAppModule.class, NetworkModule.class, PlaygroundAppApiModule.class})
@ApplicationScope
public interface PlaygroundAppComponent {

    void injectPlaygroundApp(PlaygroundApp playgroundApp);

    PlaygroundApp getPlaygroundApp();

    UploadFileApiImplementation getUploadFileApiImplementation();


}
