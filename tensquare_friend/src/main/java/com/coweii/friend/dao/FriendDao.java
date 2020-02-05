package com.coweii.friend.dao;

import com.coweii.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendDao extends JpaRepository<Friend,String> {
    /**
     * 根据userid和friendid查询好友关系
     * @param userid
     * @param friendid
     * @return
     */
    Friend findFriendByUseridAndFriendid(String userid, String friendid);
}
