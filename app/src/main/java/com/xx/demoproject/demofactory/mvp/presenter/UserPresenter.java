package com.xx.demoproject.demofactory.mvp.presenter;

import com.xx.demoproject.demofactory.mvp.bean.UserBean;
import com.xx.demoproject.demofactory.mvp.model.IUserModel;
import com.xx.demoproject.demofactory.mvp.model.SqliteUserModel;
import com.xx.demoproject.demofactory.mvp.view.IUserView;

/**
 * Created by xuxin on 2017/1/28.
 */

public class UserPresenter {

    private IUserView mUserView;
    private IUserModel mUserModel;

    public UserPresenter(IUserView userView) {
        this.mUserView = userView;
        this.mUserModel = new SqliteUserModel();
    }

    public void saveUser(String firstName, String lastName){
        long uid = this.mUserModel.saveUser(firstName, lastName);
        mUserView.setResultUid(uid);
    }

    public void loadUser(long userId){
        UserBean user = mUserModel.getUser(userId);
        if (user != null){
            mUserView.setResultStr("fname:"+user.getUserFirstName()+" lname:"+user.getUserLastName());
        }else{
            mUserView.setResultStr("no result");
        }
    }
}
