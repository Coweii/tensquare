package com.coweii.friend.dao;

import com.coweii.friend.pojo.Friend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FriendDao {

    @Insert("insert into tb_friend values(#{userid},#{friendid},#{islike})")
    void save(Friend friend);

    @Select("select * from tb_friend where userid=#{userid} and friendid=#{friendid}")
    Friend findFriendByUseridAndFriendid(String userid, String friendid);

    @Update("update tb_friend set islike=#{s} where userid=#{userid} and friendid=#{friendId}")
    void updateIslike(String s, String userid, String friendId);
}
