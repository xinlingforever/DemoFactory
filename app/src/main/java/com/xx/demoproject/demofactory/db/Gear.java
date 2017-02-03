package com.xx.demoproject.demofactory.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xuxin on 2017/2/3.
 */
@Entity
public class Gear {
    @Id(autoincrement = true)
    private Long id;
    private String gearName;
    @Generated(hash = 588375570)
    public Gear(Long id, String gearName) {
        this.id = id;
        this.gearName = gearName;
    }
    @Generated(hash = 1546799648)
    public Gear() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGearName() {
        return this.gearName;
    }
    public void setGearName(String gearName) {
        this.gearName = gearName;
    }
}
