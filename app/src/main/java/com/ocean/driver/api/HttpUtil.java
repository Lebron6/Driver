package com.ocean.driver.api;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by James on 2020/5/14.
 */
public class HttpUtil {

    public static OkHttpClient mOkHttpClient;
    public static Retrofit retrofit;
    /**
     * 初始化okhttpclient.
     * @return okhttpClient
     */
    public static OkHttpClient okhttpclient(String className) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger(className));
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();
        return mOkHttpClient;
    }

    public static DriverApi createRequest(String className, String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okhttpclient(className))
                .addConverterFactory(CustomGsonConverterFactory.create())
                .build();
        DriverApi vamApi = retrofit.create(DriverApi.class);
        return vamApi;
    }
    public static DriverApi createRequest(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okhttpclient("Nomal"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DriverApi vamApi = retrofit.create(DriverApi.class);
        return vamApi;
    }
}