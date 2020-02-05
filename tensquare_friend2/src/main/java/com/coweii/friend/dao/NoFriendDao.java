package com.coweii.friend.dao;

import com.coweii.friend.pojo.NoFriend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoFriendDao {
    @Delete("delete from tb_nofriend where userid=#{userid} and friendid=#{friendId}")
    void deleteNoFriendByUseridAndFriendid(String userid, String friendId);

    @Select("select * from tb_nofriend where userid=#{userid} and friendid=#{friendid}")
    NoFriend findByUseridAndFriendid(String userid, String friendid);
}
