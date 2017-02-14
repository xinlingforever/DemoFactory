package com.xx.demoproject.demofactory;

import android.app.Application;

import com.xx.demoproject.demofactory.db.DaoMaster;
import com.xx.demoproject.demofactory.db.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by xx on 1/4/17.
 */

public class App extends Application {
    public static final boolean ENCRYPTED = false;

    private static DaoSession daoSession;

    //private DataModelComponent mDataModelComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        //mDataModelComponent = DaggerDataModelComponent.builder().build();
    }

    //public DataModelComponent getDataModelComponent() {
        //return mDataModelComponent;
    //}

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
