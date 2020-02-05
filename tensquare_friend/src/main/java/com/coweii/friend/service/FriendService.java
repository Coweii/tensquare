package com.coweii.friend.service;

import com.coweii.friend.client.UserClient;
import com.coweii.friend.dao.FriendDao;
import com.coweii.friend.dao.NoFriendDao;
import com.coweii.friend.pojo.Friend;
import com.coweii.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class FriendService {

    @Autowired
    UserClient userClient;

    @Autowired
    FriendDao friendDao;

    @Autowired
    NoFriendDao noFriendDao;

    EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     *  添加关注
     * @param userid   用户id
     * @param friendId   被关注对象id
     * @return   1: 添加成功， 2: 已是好友关系， 3: 已被拉黑
     */
    public int addFriend(String userid, String friendId){
        entityManager.clear();

        // 查询自己是否在对方黑名单中
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(friendId, userid);
        if (noFriend != null) {  //已在对方黑名单中
            return 3;
        }

        // 查询双方朋友关系
        Friend friend = friendDao.findFriendByUseridAndFriendid(userid, friendId);
        if (friend != null) {  //已关注对方
            return 2;
        } else {   //未关注对方
            // 不管对方是否在黑名单中，先将对方从黑名单中移除
            noFriendDao.deleteNoFriendByUseridAndFriendid(userid, friendId);
        }
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendId);

        //查询自己是否为对方好友
        Friend friend1;
        friend1 = friendDao.findFriendByUseridAndFriendid(friendId, userid);
        if (friend1 == null) {  //并非对方好友
            friend.setIslike("0");
        } else {   // 已是对方好友
            friend1.setIslike("1");
            friend.setIslike("1");
            friendDao.save(friend1);
            //双方已建立关系，无需另外添加表数据，直接在原有表数据上作修改
        }

        friendDao.save(friend);

        entityManager.flush();

        //更新对方的粉丝数
        userClient.updateFansCount(friendId, 1);  //(事务不管用，可能是需要分布式事务)
        //更新本用户的关注数
        userClient.updateFollowCount(userid, 1);

        return 1;

    }
}
