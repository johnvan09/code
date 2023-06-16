package cdp.fms.dao;

import cdp.fms.model.BillBook;

import java.util.List;

public interface BillBookDao {
    /**
     * 添加账单
     **/
    boolean addBillBook(BillBook billBook);

    /**
     * 修改账单
     **/
    boolean changeBillBook(BillBook billBook);

    /**
     * 删除账单
     **/
    boolean deleteBillBookById(int bid);

    /**
     * 通过id查询账单
     **/
    BillBook getBillBookById(int bid);

    /**
     * 获取账单列表
     **/
    List<BillBook> getBillBookList(int userId);

    /**
     * 按分类获取账单
     **/
    List<BillBook> getBillBookByCategory(int categoryId,int userId);

    /**
     * 统计某月收支情况
     **/
    List<BillBook> getMonthIncome(int month,int userId);

    List<BillBook> getMonthOutcome(int month,int userId);


    /**
     * 统计每年收支情况
     **/
    List<BillBook> getYearIncome(int year,int userId);

    List<BillBook> getYearOutcome(int year,int userId);
}
