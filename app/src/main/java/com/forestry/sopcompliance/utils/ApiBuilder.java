package com.forestry.sopcompliance.utils;

import com.forestry.sopcompliance.AppController;
import com.forestry.sopcompliance.BuildConfig;
import com.forestry.sopcompliance.data.remote.UnauthorisedInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by fimansya on 5/8/2017.
 */

public class ApiBuilder {
    public static Gson constructDefaultGson() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        return gson;
    }


    public static Retrofit buildAuthApiServiceClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Strategy strategy = new AnnotationStrategy();

        Serializer serializer = new Persister(strategy);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit =  new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                .baseUrl("http://smfweb17.smf.com/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }



    public static Retrofit buildHotspotApiClient(final AppController app) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        //builder.certificatePinner(new CertificatePinner.Builder().add("*.androidadvance.com", "sha256/RqzElicVPA6LkKm9HblOvNOUqWmD+4zNXcRb+WjcaAE=")
        //    .add("*.xxxxxx.com", "sha256/8Rw90Ej3Ttt8RRkrg+WYDS9n7IS03bk5bjP/UXPtaY8=")
        //    .add("*.xxxxxxx.com", "sha256/Ko8tivDrEjiY90yGasP6ZpBU4jwXvHqVvQI0GS3GNdA=")
        //    .add("*.xxxxxxx.com", "sha256/VjLZe/p3W/PJnd6lL8JVNBCGQBZynFLdZSTIqcO0SJ8=")
        //    .build());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(interceptor);
        }

        //Extra Headers

        //builder.addNetworkInterceptor().add(chain -> {
        //  Request request = chain.request().newBuilder().addHeader("Authorization", authToken).build();
        //  return chain.proceed(request);
        //});

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(app.getCacheDir(), cacheSize);
        builder.cache(cache);

        //builder.addInterceptor(new UnauthorisedInterceptor(app));
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(app.getConfig().getApiBaseUrl())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(constructDefaultGson()))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

        return retrofit;
    }

    public static Retrofit buildSecureResourceApiClient(final AppController app) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        //builder.certificatePinner(new CertificatePinner.Builder().add("*.androidadvance.com", "sha256/RqzElicVPA6LkKm9HblOvNOUqWmD+4zNXcRb+WjcaAE=")
        //    .add("*.xxxxxx.com", "sha256/8Rw90Ej3Ttt8RRkrg+WYDS9n7IS03bk5bjP/UXPtaY8=")
        //    .add("*.xxxxxxx.com", "sha256/Ko8tivDrEjiY90yGasP6ZpBU4jwXvHqVvQI0GS3GNdA=")
        //    .add("*.xxxxxxx.com", "sha256/VjLZe/p3W/PJnd6lL8JVNBCGQBZynFLdZSTIqcO0SJ8=")
        //    .build());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(interceptor);
        }




        //Extra Headers

        //builder.addNetworkInterceptor().add(chain -> {
        //  Request request = chain.request().newBuilder().addHeader("Authorization", authToken).build();
        //  return chain.proceed(request);
        //});

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(app.getCacheDir(), cacheSize);
        builder.cache(cache);

        builder.addInterceptor(new UnauthorisedInterceptor(app));
        OkHttpClient client = builder.build();

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(app.getConfig().getApiResourceUrl())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(constructDefaultGson()))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

        return retrofit;
    }


    public static Retrofit buildHotspotPostApiClient(final AppController app) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(1, TimeUnit.MINUTES);
        builder.connectTimeout(5, TimeUnit.MINUTES);
        builder.writeTimeout(5, TimeUnit.MINUTES);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(interceptor);
        }

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(app.getCacheDir(), cacheSize);
        builder.cache(cache);

        //builder.addInterceptor(new UnauthorisedInterceptor(app));
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(app.getConfig().getApiBaseUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit;
    }


    public static Retrofit buildDistrictAuthApiServiceClient(final AppController app) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(interceptor);
        }

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(app.getCacheDir(), cacheSize);
        builder.cache(cache);

        //builder.addInterceptor(new UnauthorisedInterceptor(app));
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(app.getConfig().getApiBaseUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit;
    }

    public static Retrofit buildGisDataApiServiceClient(final AppController app) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(interceptor);
        }

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(app.getCacheDir(), cacheSize);
        builder.cache(cache);

        //builder.addInterceptor(new UnauthorisedInterceptor(app));
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(app.getConfig().getApiBaseUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit;
    }



}
