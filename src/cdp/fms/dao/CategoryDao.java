package cdp.fms.dao;

import cdp.fms.model.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 添加分类
     **/
    boolean addCategory(Category category);

    /**
     * 分类是否重复
     **/
    boolean isUserExisted(String key, String value);

    /**
     * 修改分类
     **/
    boolean changeCategory(Category category);

    /**
     * 获取所有分类
     **/
    List<Category> getAllCategories();

    /**
     * 删除分类
     **/
    boolean deleteCategoryById(int categoryId);

    /**
     * 根据id获取分类
     **/
    Category getCategoryById(int categoryId);
}
