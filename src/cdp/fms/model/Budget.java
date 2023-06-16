package cdp.fms.model;

import java.util.Date;

public class Budget {
    //预算
    private  int buid;
    private User user;
    private Category category;
    private String buname;
    private float bmoney;
    private String binfo;
    private Date btime;

    public int getBuid() {
        return buid;
    }

    public void setBuid(int buid) {
        this.buid = buid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBuname() {
        return buname;
    }

    public void setBuname(String buname) {
        this.buname = buname;
    }

    public float getBmoney() {
        return bmoney;
    }

    public void setBmoney(float bmoney) {
        this.bmoney = bmoney;
    }

    public String getBinfo() {
        return binfo;
    }

    public void setBinfo(String binfo) {
        this.binfo = binfo;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "buid=" + buid +
                ", user=" + user +
                ", category=" + category +
                ", buname='" + buname + '\'' +
                ", bmoney=" + bmoney +
                ", binfo='" + binfo + '\'' +
                ", btime=" + btime +
                '}';
    }
}
