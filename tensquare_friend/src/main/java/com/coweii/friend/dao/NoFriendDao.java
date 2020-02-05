package com.coweii.friend.dao;

import com.coweii.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    //查询黑名单关系
    @Query(value = "select * from tb_nofriend where userid=? and friendid=?", nativeQuery = true)
    NoFriend findByUseridAndFriendid(String uesrid, String friendid);

    //解除黑名单
    void deleteNoFriendByUseridAndFriendid(String userid, String friendid);
}
