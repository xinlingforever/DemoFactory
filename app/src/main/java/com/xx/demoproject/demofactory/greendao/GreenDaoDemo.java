package com.xx.demoproject.demofactory.greendao;


import android.util.Log;

import com.xx.demoproject.demofactory.App;
import com.xx.demoproject.demofactory.db.DaoSession;
import com.xx.demoproject.demofactory.db.User;
import com.xx.demoproject.demofactory.db.UserDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * Created by xx on 1/4/17.
 */

public class GreenDaoDemo {

    private static UserDao userDao;
    private static Query<User> userQuery;

    public static void doTest() {
        // get the note DAO
        DaoSession daoSession = App.getDaoSession();
        userDao = daoSession.getUserDao();

        for (int i=0; i<10; i++){
            User _user = new User();
            _user.setFirstName("fname_"+i);
            _user.setLastName("lname_"+i);
            Long _id = userDao.insertOrReplace(_user);
            Log.d("xx", "insert id:"+_id);
        }

        // query all notes, sorted a-z by their text
        userQuery = userDao.queryBuilder().orderAsc(UserDao.Properties.Id).build();
        List<User> userList = userQuery.list();
        for (User user : userList){
            Log.d("xx", "id:"+user.getId()+" fname:"+user.getFirstName()+" lname:"+user.getLastName());
        }


    }
}
