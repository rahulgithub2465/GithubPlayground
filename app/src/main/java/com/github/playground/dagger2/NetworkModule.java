package com.github.playground.dagger2;

import com.github.playground.BuildConfig;
import com.github.playground.multipart.constants.ApiUrls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.RETROFIT_LOG_LEVEL);
        return interceptor;
    }

    @Provides
    @ApplicationScope
    Converter.Factory providesConverterFactory() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return GsonConverterFactory.create(gson);
    }


    @Provides
    @ApplicationScope
    Retrofit providesApiClient(final HttpLoggingInterceptor interceptor, Converter.Factory converterFactory) {
        return new Retrofit.Builder().
                baseUrl(ApiUrls.BASE_URL).
                addConverterFactory(converterFactory).build();
    }
}
