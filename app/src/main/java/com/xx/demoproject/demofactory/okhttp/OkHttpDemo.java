package com.xx.demoproject.demofactory.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xuxin on 2017/1/18.
 */

public class OkHttpDemo {

    private static final String TAG = "OkHttpDemo";

    private static final String TEST_URL = "http://www.baidu.com";

    public static void doTest() {
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
                    Log.d(TAG, "body:"+response.body().string());
                }
            }
        });
    }
}
