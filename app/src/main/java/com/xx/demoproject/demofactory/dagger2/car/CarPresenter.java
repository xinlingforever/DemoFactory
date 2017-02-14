package com.xx.demoproject.demofactory.dagger2.car;

import android.util.Log;

import com.xx.demoproject.demofactory.dagger2.data.DataModel;

import javax.inject.Inject;

/**
 * Created by xuxin on 2017/2/3.
 */

public class CarPresenter implements ICarContract.IPresenter {

    private ICarContract.IView mView;
    private DataModel mModel;

    @Inject
    CarPresenter(ICarContract.IView view, DataModel model) {
        this.mView = view;
        this.mModel = model;
    }

    @Inject
    void setupListener(){
        mView.setPresenter(this);
    }

    @Inject
    void print() {
        Log.d(CarActivity.TAG, "in presenter inject method print");
    }

    @Override
    public void loadGearName() {
        long id = mModel.saveDefaultGearName();
        Log.d(CarActivity.TAG, "save default gear id:"+id);
        String gearName = mModel.getGearName(id);
        mView.showGearName(gearName);
    }
}
