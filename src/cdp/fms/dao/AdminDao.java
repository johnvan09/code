package cdp.fms.dao;

import cdp.fms.model.Admin;

import java.util.List;

public interface AdminDao {
    /**
     * 根据编号获取管理员（编辑）
     * @param adminId
     * @return
     */
    Admin getAdminById(int adminId);


    /**
     * 管理员（编辑）登陆
     * @param account (账号/手机号/电子邮件)
     * @param password
     * @return
     */
    Admin login(String account, String password);

    /**
     * 获取所有管理员
     * @return
     */
    List<Admin> getAllAdmins();

    /**
     * 新增管理员
     * @param admin
     */
    boolean add(Admin admin);

    /**
     * 更新管理员
     * @param admin
     */
    void update(Admin admin);

    /**
     * 删除管理员
     * @param adminId
     */
    void delete(int adminId);
}
