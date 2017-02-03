package com.xx.demoproject.demofactory.dagger2.car;

import android.annotation.FractionRes;

import com.xx.demoproject.demofactory.dagger2.FragmentScoped;
import com.xx.demoproject.demofactory.dagger2.data.DataModel;
import com.xx.demoproject.demofactory.dagger2.data.DataModelComponent;

import dagger.Component;

/**
 * Created by xuxin on 2017/2/3.
 */
@FragmentScoped
@Component (dependencies = DataModelComponent.class, modules = CarPresenterModule.class)
public interface CarComponent {
    void inject(CarActivity carActivity);
}
