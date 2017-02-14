package com.xx.demoproject.demofactory.dagger2.car;

import com.xx.demoproject.demofactory.dagger2.data.IDataModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xuxin on 2017/2/3.
 */
@Module
public class CarPresenterModule {

    private final ICarContract.IView mView;
    private final IDataModel mDataModel;


    public CarPresenterModule(ICarContract.IView view, IDataModel model){
        this.mView = view;
        this.mDataModel = model;
    }

    @Provides
    ICarContract.IView provideICarContractView() {
        return this.mView;
    }

    @Provides
    IDataModel provideIDataModel() {
        return this.mDataModel;
    }
}
