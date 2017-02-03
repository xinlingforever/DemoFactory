package com.xx.demoproject.demofactory.dagger2.data;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xuxin on 2017/2/3.
 */
@Module
public class DataModelModule {
    private IDataModel mModel;

    DataModelModule(IDataModel dataModel){
        this.mModel = dataModel;
    }

    @Provides
    IDataModel provideIDataModel(){
        return mModel;
    }
}
