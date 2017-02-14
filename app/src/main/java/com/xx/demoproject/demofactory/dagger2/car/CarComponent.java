package com.xx.demoproject.demofactory.dagger2.car;

import com.xx.demoproject.demofactory.dagger2.FragmentScoped;
import com.xx.demoproject.demofactory.dagger2.data.DataModelComponent;
import com.xx.demoproject.demofactory.dagger2.data.DataModelModule;

import dagger.Component;

/**
 * Created by xuxin on 2017/2/3.
 */

@Component (modules = {CarPresenterModule.class})
public interface CarComponent {
    void inject(CarActivity carActivity);
}
