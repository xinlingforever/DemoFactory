package com.xx.demoproject.demofactory.rxjava;


import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by xx on 11/14/16.
 */

public class RxJavaDemo {
    public static void doTest() {
        String[] names = {"abc","def","ghi","jkl"};

        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("xx", "s:"+s);
                    }
                });

        myObservable2.subscribe(onNextAction);
        myTest();
        myTest2();
    }

    private static Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> sub) {
                    sub.onNext("hello");
                    sub.onCompleted();
                }

    });

    private static Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) {
            Log.d("xx", s);
        }
        @Override
        public void onCompleted() {
        }
        @Override
        public void onError(Throwable e){
        }
    };

    private static Observable<String> myObservable2 = Observable.just("hello2");
    private static Action1<String> onNextAction = new Action1<String>() {
        public void call(String s){
            Log.d("xx", s);
        }
    };

    private static void myTest(){
        Observable.just("hello3")
                .subscribe(new Action1<String>() {
                    public void call(String s){
                        Log.d("xx", s);
                    }
                });
    }

    private static void myTest2() {
        Observable.just("hello4")
                .map(s -> s + " -xx")
                .subscribe(s -> Log.d("xx", s));
    }




}
