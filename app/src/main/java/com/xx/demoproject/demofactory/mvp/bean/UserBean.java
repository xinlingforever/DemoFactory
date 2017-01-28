package com.xx.demoproject.demofactory.mvp.bean;

/**
 * Created by xuxin on 2017/1/28.
 */

public class UserBean {
    private String mUserFirstName;
    private String mUserLastName;

    public UserBean(String userFirstName, String userLastName) {
        this.mUserFirstName = userFirstName;
        this.mUserLastName = userLastName;
    }

    public String getUserFirstName() {
        return this.mUserFirstName;
    }

    public String getUserLastName() {
        return this.mUserLastName;
    }


}
