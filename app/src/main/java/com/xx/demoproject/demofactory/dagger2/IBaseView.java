package com.xx.demoproject.demofactory.dagger2;

/**
 * Created by xuxin on 2017/2/3.
 */

public interface IBaseView<T> {
    void setPresenter(T presenter);
}
