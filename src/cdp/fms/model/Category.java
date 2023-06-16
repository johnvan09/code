package cdp.fms.model;

public class Category {
    //记账分类
    private int id;
    private String inorout;//收入或支出
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInorout() {
        return inorout;
    }

    public void setInorout(String inorout) {
        this.inorout = inorout;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", inorout='" + inorout + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
