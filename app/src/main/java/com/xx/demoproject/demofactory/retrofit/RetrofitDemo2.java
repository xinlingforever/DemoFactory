package com.xx.demoproject.demofactory.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by xuxin on 2017/1/19.
 */

public class RetrofitDemo2 {

    private static final String TAG = "RetrofitDemo2";

    public static final int TYPE_SYNC = 0;
    public static final int TYPE_ASYNC = 1;

    public static final int TYPE_GET = 0;
    public static final int TYPE_POST = 1;

    class BookResponse {
        int count;
        int start;
        int total;
        List<Books> books;


        BookResponse(int count, int start, int total, List<Books> books){
            this.count = count;
            this.start = start;
            this.total = total;
            this.books = books;
        }

        class Books {
            Rating rating;
            String subtitle;
            List<String> author;
            String pubdate;
            List<Tag> tags;
            String origin_title;
            String image;
            String binding;
            List<String> translator;
            String catalog;
            String pages;
            Image images;
            String alt;
            String id;
            String publisher;
            String isbn10;
            String isbn13;
            String title;
            String url;
            String alt_title;
            String author_intro;
            String price;
        }

        class Rating {
            int max;
            int numRaters;
            String average;
            int min;
        }

        class Tag {
            int count;
            String name;
            String title;
        }

        class Image {
            String small;
            String large;
            String medium;
        }

        @Override
        public String toString() {
            return "count:"+this.count+" start:"+this.start+" total:"+total+" book.title:"+this.books.get(0).title;
        }
    }

    public interface DouBanService {
        @GET("book/search")
        Call<BookResponse> getSearchBooks(
                @Query("q") String name,
                @Query("tag") String tag,
                @Query("start") int start,
                @Query("count") int count
        );

        @FormUrlEncoded
        @POST("book/search")
        Call<BookResponse> getSearchBooksByPost(
                @Field("q") String name,
                @Field("tag") String tag,
                @Field("start") int start,
                @Field("count") int count
        );
    }

    public static void doTest(final int requestType, final int handleType){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DouBanService douBanService = retrofit.create(DouBanService.class);

        Call<BookResponse> call;
        if (requestType == TYPE_GET) {
            call = douBanService.getSearchBooks("小王子", null, 0, 3);
        }else{
            call = douBanService.getSearchBooksByPost("小王子", null, 0, 3);
        }

        switch (handleType) {
            case TYPE_SYNC:
                syncRequest(call);
                break;
            case TYPE_ASYNC:
                asyncRequest(call);
                break;
            default:
                break;
        }

    }

    //sync get request
    private static void syncRequest(Call call) {
        if (call != null) {
            try {
                Response response = call.execute();
                if (response.isSuccessful()) {
                    Log.d(TAG, "sync request:" + call.request().toString());
                    Log.d(TAG, "sync:" + response.toString());
                }else{
                    Log.d(TAG, "sync fail, code:"+response.code());
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //async get request
    private static void asyncRequest(Call call) {
        if (call != null) {
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "async request:" + call.request().toString());
                        Log.d(TAG, "async succ:" + response.body().toString());
                    }else{
                        Log.d(TAG, "async fail: code:"+response.code());
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d(TAG, "async request fail");
                }
            });
        }
    }

}
