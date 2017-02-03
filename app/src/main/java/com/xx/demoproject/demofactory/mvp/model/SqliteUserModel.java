package com.xx.demoproject.demofactory.mvp.model;

import android.text.TextUtils;

import com.xx.demoproject.demofactory.App;
import com.xx.demoproject.demofactory.db.DaoSession;
import com.xx.demoproject.demofactory.db.UserDao;
import com.xx.demoproject.demofactory.db.User;
import com.xx.demoproject.demofactory.mvp.bean.UserBean;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * Created by xuxin on 2017/1/28.
 */

public class SqliteUserModel implements IUserModel {
    @Override
    public long saveUser(String firstName, String lastName) {
        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)){
            return -1;
        }
        DaoSession daoSession = App.getDaoSession();
        if (daoSession != null){
            UserDao userDao = daoSession.getUserDao();
            if (userDao != null){
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                long id = userDao.insertOrReplace(user);
                return id;
            }
        }
        return -1;
    }

    @Override
    public UserBean getUser(long id) {
        DaoSession daoSession = App.getDaoSession();
        if (daoSession != null) {
            UserDao userDao = daoSession.getUserDao();
            if (userDao != null){
                Query<User> userQuery = userDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).build();
                List<User> userList = userQuery.list();
                if (userList != null && userList.size() == 1){
                    User user = userList.get(0);
                    String fname = user.getFirstName();
                    String lname = user.getLastName();
                    UserBean userBean = new UserBean(fname, lname);
                    return userBean;
                }
            }
        }

        return null;
    }
}
