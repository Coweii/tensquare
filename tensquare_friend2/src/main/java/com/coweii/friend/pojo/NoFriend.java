package com.coweii.friend.pojo;

import java.io.Serializable;

public class NoFriend implements Serializable {  // 黑名单关系
    String userid;
    String friendid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }
}
