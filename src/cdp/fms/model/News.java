package cdp.fms.model;

import java.util.Date;

public class News {
    //财务新闻
    private int nid;
    private String name;
    private String ninfo;
    private String nimg;
    private String writer;
    private int nums;
    private Date ncdate;

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNinfo() {
        return ninfo;
    }

    public void setNinfo(String ninfo) {
        this.ninfo = ninfo;
    }

    public String getNimg() {
        return nimg;
    }

    public void setNimg(String nimg) {
        this.nimg = nimg;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public Date getNcdate() {
        return ncdate;
    }

    public void setNcdate(Date ncdate) {
        this.ncdate = ncdate;
    }

    @Override
    public String toString() {
        return "News{" +
                "nid=" + nid +
                ", name='" + name + '\'' +
                ", ninfo='" + ninfo + '\'' +
                ", nimg='" + nimg + '\'' +
                ", writer='" + writer + '\'' +
                ", nums=" + nums +
                ", ncdate=" + ncdate +
                '}';
    }
}
