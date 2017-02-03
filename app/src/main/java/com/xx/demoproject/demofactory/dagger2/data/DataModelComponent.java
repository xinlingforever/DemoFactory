package com.xx.demoproject.demofactory.dagger2.data;

import com.xx.demoproject.demofactory.dagger2.car.CarPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xuxin on 2017/2/3.
 */
@Singleton
@Component
public interface DataModelComponent {
    DataModel getDataMode();
}
