package cdp.fms.dao;

import cdp.fms.model.User;

import java.util.List;

public interface UserDao {
    /**
     * 登录
     *
     * @return
     */
    User loginUser(String account, String psd);

    /**
     * 添加用户(注册）
     **/
    boolean addUser(User user);

    boolean isUserExisted(String key, String value);

    /**
     * 修改用户
     **/
    boolean changeUser(User user);

    /**
     * 修改密码
     **/
    boolean changePsd(String account, String psd);

    /**
     * 禁用用户
     **/
    boolean forbiddenUser(int userId);

    /**
     * 通过id查找用户
     **/
    User searchUserById(int userId);
    User getUserByAccount(String account);

    /**
     * 获取所有用户
     **/
    List<User> getAllUsers();

    /**
     * 加好友
     **/
    boolean makeFriends(int userId1, int userId2);

    /**
     * 解除好友关系
     **/
    boolean overFriend(int userId1, int userId2);

    /**
     * 通过用户id查找到好友
     **/
    List<User> getFriendsById(int userId);


}
