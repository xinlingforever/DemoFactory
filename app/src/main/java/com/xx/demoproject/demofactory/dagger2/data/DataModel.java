package com.xx.demoproject.demofactory.dagger2.data;

import com.xx.demoproject.demofactory.App;
import com.xx.demoproject.demofactory.db.DaoSession;
import com.xx.demoproject.demofactory.db.Gear;
import com.xx.demoproject.demofactory.db.GearDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by xuxin on 2017/2/3.
 */
@Singleton
public class DataModel implements IDataModel {

    @Inject
    DataModel(){

    }

    @Override
    public String getGearName(long id) {
        DaoSession daoSession = App.getDaoSession();
        if (daoSession != null){
            GearDao gearDao = daoSession.getGearDao();
            if (gearDao != null){
                Query<Gear> query = gearDao.queryBuilder().where(GearDao.Properties.Id.eq(id)).build();
                List<Gear> list = query.list();
                if (list != null && list.size() == 1){
                    Gear gear = list.get(0);
                    return gear.getGearName();
                }
            }
        }
        return null;
    }

    @Override
    public long saveDefaultGearName() {
        DaoSession daoSession = App.getDaoSession();
        if (daoSession != null){
            GearDao gearDao = daoSession.getGearDao();
            if (gearDao != null){
                Gear gear = new Gear();
                gear.setGearName("name in greenDao");
                long id = gearDao.insertOrReplace(gear);
                return id;
            }
        }
        return -1;
    }
}
