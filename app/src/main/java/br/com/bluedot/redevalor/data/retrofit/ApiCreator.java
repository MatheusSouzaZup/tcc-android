package br.com.bluedot.redevalor.data.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gadal on 27/10/17.
 */

public class ApiCreator {

    //todo set base url
    public static String API_BASE_URL = ""; //BuildConfig.HOST;

    private static OkHttpClient httpClient = RetrofitSetup.getOkHttpClient();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }


    public static <S> S createService(Class<S> serviceClass, String patternDateFormat) {
        Gson gsonDateFormat = new GsonBuilder().setDateFormat(patternDateFormat).create();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonDateFormat));

        return builder.client(httpClient).build().create(serviceClass);
    }


}
