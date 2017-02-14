package com.xx.demoproject.demofactory.retrofit;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
 * 一个Retrofit2+Rxjava2的例子
 *
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
        Observable<BookResponse> getSearchBooksByGet(
                @Query("q") String name,
                @Query("tag") String tag,
                @Query("start") int start,
                @Query("count") int count
        );

        @FormUrlEncoded
        @POST("book/search")
        Observable<BookResponse> getSearchBooksByPost(
                @Field("q") String name,
                @Field("tag") String tag,
                @Field("start") int start,
                @Field("count") int count
        );
    }

    public static void doTest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        DouBanService douBanService = retrofit.create(DouBanService.class);

        douBanService.getSearchBooksByGet("小王子", null, 0, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(BookResponse bookResponse) {
                        Log.d(TAG, "onNext info:" + bookResponse.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError:" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }
}
