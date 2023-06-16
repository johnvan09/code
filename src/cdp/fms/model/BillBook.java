package cdp.fms.model;

import java.util.Date;

public class BillBook {
    //记账
    private int bid;
    private String bname;
    private User user;
    private Category category;
    private float money;
    private String way;//支付方式
    private Date bcdate;//创建时间
    private String beizhu;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Date getBcdate() {
        return bcdate;
    }

    public void setBcdate(Date bcdate) {
        this.bcdate = bcdate;
    }


    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    @Override
    public String toString() {
        return "BillBook{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", user=" + user +
                ", category=" + category +
                ", money=" + money +
                ", way='" + way + '\'' +
                ", bcdate=" + bcdate +
                ", beizhu='" + beizhu + '\'' +
                '}';
    }
}
