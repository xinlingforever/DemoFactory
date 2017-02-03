package com.xx.demoproject.demofactory.dagger2.car;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xuxin on 2017/2/3.
 */
@Module
public class CarPresenterModule {

    private final ICarContract.IView mView;


    public CarPresenterModule(ICarContract.IView view){
        this.mView = view;
    }

    @Provides
    ICarContract.IView provideICarContractView() {
        return this.mView;
    }
}
