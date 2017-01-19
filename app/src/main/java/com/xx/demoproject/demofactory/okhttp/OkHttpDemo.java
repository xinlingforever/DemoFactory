package com.xx.demoproject.demofactory.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xuxin on 2017/1/18.
 */

public class OkHttpDemo {

    private static final String TAG = "OkHttpDemo";

    private static final String TEST_URL = "http://www.baidu.com";

    public static final int TYPE_SYNC_GET = 0;
    public static final int TYPE_ASYNC_GET = 1;
    public static final int TYPE_SYNC_POST = 2;
    public static final int TYPE_ASYNC_POST = 3;

    public static void doTest(int type) {
        switch (type) {
            case TYPE_SYNC_GET:
                syncGetRequest();
                break;
            case TYPE_ASYNC_GET:
                asyncGetRequest();
                break;
            case TYPE_SYNC_POST:
                syncPostRequest();
                break;
            case TYPE_ASYNC_POST:
                asyncPostRequest();
                break;
            default:
                break;
        }

    }

    //sync get request
    private static void syncGetRequest() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(TEST_URL).build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            Log.d(TAG, "sync get response body:"+response.body().string());
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    //async get request
    private static void asyncGetRequest() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(TEST_URL).build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null) {
                    Log.d(TAG, "async get response body:"+response.body().string());
                }
            }
        });
    }

    //sync post request
    private static void syncPostRequest() {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("num", "1").build();
        Request request = new Request.Builder().url(TEST_URL).post(requestBody).build();

        Call call = client.newCall(request);

        try {
            Response response = call.execute();
            Log.d(TAG, "sync post response body:"+response.body().string());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //async post request
    private static void asyncPostRequest() {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("size", "10").build();
        Request request = new Request.Builder().url(TEST_URL).post(requestBody).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "asyncPostRequest fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "asyncPostRequest response code:"+response.code()+" body:"+response.body().string());
            }
        });
    }
}
