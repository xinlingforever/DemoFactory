package com.xx.demoproject.demofactory.dagger2.data;

/**
 * Created by xuxin on 2017/2/3.
 */

public interface IDataModel {
    String getGearName(long id);
    long saveDefaultGearName();
}
