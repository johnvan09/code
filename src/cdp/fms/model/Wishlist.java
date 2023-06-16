package cdp.fms.model;

import java.sql.Timestamp;
import java.util.Date;

public class Wishlist {
    //心愿
    private int wid;
    private String  wname;
    private float wAllMoney;
    private float whaveMoney;
    private java.sql.Date wcdate;
    private java.sql.Date wfdate;
    private String wstatus; //状态

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public float getwAllMoney() {
        return wAllMoney;
    }

    public void setwAllMoney(float wAllMoney) {
        this.wAllMoney = wAllMoney;
    }

    public float getWhaveMoney() {
        return whaveMoney;
    }

    public void setWhaveMoney(float whaveMoney) {
        this.whaveMoney = whaveMoney;
    }

    public Date getWcdate() {
        return wcdate;
    }

    public void setWcdate(java.sql.Date wcdate) {
        this.wcdate = wcdate;
    }

    public Date getWfdate() {
        return wfdate;
    }

    public void setWfdate(java.sql.Date wfdate) {
        this.wfdate = wfdate;
    }

    public String getWstatus() {
        return wstatus;
    }

    public void setWstatus(String wstatus) {
        this.wstatus = wstatus;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "wid=" + wid +
                ", wname='" + wname + '\'' +
                ", wAllMoney=" + wAllMoney +
                ", whaveMoney=" + whaveMoney +
                ", wcdate=" + wcdate +
                ", wfdate=" + wfdate +
                ", wstatus='" + wstatus + '\'' +
                '}';
    }
}
