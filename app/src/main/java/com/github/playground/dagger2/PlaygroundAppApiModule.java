package com.github.playground.dagger2;

import com.github.playground.multipart.apiClient.UploadFileClient;
import com.github.playground.multipart.apiImplementations.UploadFileApiImplementation;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class PlaygroundAppApiModule {

    @ApplicationScope
    @Provides
    UploadFileClient providesUploadFileClient(Retrofit retrofit) {
        return retrofit.create(UploadFileClient.class);
    }

    @ApplicationScope
    @Provides
    UploadFileApiImplementation providesUploadFileApiImplementation(UploadFileClient uploadFileClient) {
        return new UploadFileApiImplementation(uploadFileClient);
    }
}
