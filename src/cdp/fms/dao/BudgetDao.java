package cdp.fms.dao;

import cdp.fms.model.Budget;

import java.util.List;

public interface BudgetDao {
    /**
     * 添加预算信息
     **/
    boolean addBudget(Budget budget);

    /**
     * 查找预算信息
     **/
    Budget getBudgetById(int id);

    List<Budget> getBudget(int userId);

    /**
     * 删除预算信息
     **/
    boolean deleteBudget(int id);

    /**
     * 修改预算信息
     **/
    boolean changeBudget(Budget budget);

    /**
     * 根据分类获得预算
     **/
    List<Budget> getBudgetList(int categoryId, int userId);
}
