package com.xx.demoproject.demofactory.dagger2.car;

import com.xx.demoproject.demofactory.dagger2.IBaseView;

/**
 * Created by xuxin on 2017/2/3.
 */

public interface ICarContract {
    interface IView extends IBaseView<IPresenter> {
        void showGearName(String gearName);
    }

    interface IPresenter {
        void loadGearName();
    }
}
