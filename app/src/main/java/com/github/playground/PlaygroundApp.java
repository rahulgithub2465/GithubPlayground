package com.github.playground;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.github.playground.dagger2.DaggerPlaygroundAppComponent;
import com.github.playground.dagger2.PlaygroundAppComponent;
import com.github.playground.dagger2.PlaygroundAppModule;

import java.io.File;

public class PlaygroundApp extends Application {

    public static String path = "";
    private PlaygroundAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerPlaygroundAppComponent
                .builder()
                .playgroundAppModule(new PlaygroundAppModule(this))
                .build();
        component.injectPlaygroundApp(this);
        path = Environment.getExternalStorageDirectory() + File.separator + "Playground";
    }

    public static PlaygroundApp get(Context context) {
        return (PlaygroundApp) context.getApplicationContext();
    }

    public PlaygroundAppComponent getComponent() {
        return component;
    }


}
