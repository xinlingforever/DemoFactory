package com.xx.demoproject.demofactory.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xx on 11/11/16.
 */

public class RetrofitDemo {
    public static final String API_URL = "https://api.github.com";

    public static class Contributor {
        public final String login;
        public final int contributions;
        public final int id;

        public Contributor(String login, int contributions, int id) {
            this.login = login;
            this.contributions = contributions;
            this.id = id;
        }
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

    public static void doTest(int type) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        //.header("Authorization", "auth-token")
                        .method(original.method(), original.body())
                        .build();

                okhttp3.Response response = chain.proceed(request);

                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = github.contributors("xinlingforever", "demofactory");

        if (type == 1) {
            // async
            call.enqueue(new Callback<List<Contributor>>() {
                @Override
                public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                    try {
                        for (Contributor contributor : response.body()) {
                            Log.d("xx", "async, " + contributor.login + " (" + contributor.contributions + ")" + " id:"+contributor.id);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<List<Contributor>> call, Throwable t) {

                }
            });
        }else{
            // sync
            // Fetch and print a list of the contributors to the library.
            try {
                List<Contributor> contributors = call.execute().body();
                for (Contributor contributor : contributors) {
                    Log.d("xx", "sync" + contributor.login + " (" + contributor.contributions + ")" + " id1:"+contributor.id);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
