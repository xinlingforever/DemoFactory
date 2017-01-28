package com.xx.demoproject.demofactory.mvp.model;

import com.xx.demoproject.demofactory.mvp.bean.UserBean;

/**
 * Created by xuxin on 2017/1/28.
 */

public interface IUserModel {
    long saveUser(String firstName, String lastName);
    UserBean getUser(long id);
}
