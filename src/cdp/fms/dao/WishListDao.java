package cdp.fms.dao;

import cdp.fms.model.Wishlist;

import java.util.List;

public interface WishListDao {
    /**
     * 添加个人的心愿单
     **/
    boolean addWish(Wishlist wishlist,int userId);

    boolean isWishExisted(String key, String valueOf);

    /**
     * 邀请好友添加共同完成心愿单
     **/
    boolean inviteFriendsWishlist(Wishlist wishlist,int userId1,int userId2);

    /**
     * 查询心愿单
     * 查询某用户的心愿单 管理员不可见
     */
    List<Wishlist> getWishlistByUserId(int userId);

    /**
     * 修改心愿单 发起邀请的人才具有修改权
     **/
    boolean changeWishlist(Wishlist wishlist);

    /**
     * 删除心愿单
     * 如果有邀请的好友 则把invite表中的关联数据一同删除
     **/
    boolean deleteWishlist(int id);

    Wishlist getWishlistById(int wid);
}
