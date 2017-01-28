package com.xx.demoproject.demofactory.mvp.view;

/**
 * Created by xuxin on 2017/1/28.
 */

public interface IUserView {
    long getUid();
    String getUserFirstName();
    String getUserLastName();
    void setResultUid(long uid);
    void setResultStr(String result);
}
