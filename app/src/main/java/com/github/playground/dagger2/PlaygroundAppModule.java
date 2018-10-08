package com.github.playground.dagger2;

import android.app.Application;
import android.content.Context;

import com.github.playground.PlaygroundApp;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaygroundAppModule {

    private PlaygroundApp playgroundApp;

    public PlaygroundAppModule(PlaygroundApp playgroundApp) {
        this.playgroundApp = playgroundApp;
    }

    @Provides
    @ApplicationScope
    PlaygroundApp providesPlaygroundApp() {
        return playgroundApp;
    }

    @Provides
    @ApplicationScope
    Context providesContext(PlaygroundApp playgroundApp) {
        return playgroundApp.getApplicationContext();
    }

}

