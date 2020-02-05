package com.coweii.friend.controller;

import com.coweii.common.entity.Result;
import com.coweii.common.entity.StatusCode;
import com.coweii.friend.service.FriendService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    FriendService friendService;

    @PutMapping("/like/{friendid}/{type}")
    public Result friend(@PathVariable("friendid") String friendId, @PathVariable("type") String type){
        Claims claims = (Claims) request.getAttribute("user_claims");
        if(claims ==null){
            return new Result(false, StatusCode.ACCESSERROR,"请先登录");
        }

        String userid = claims.getId();

        if(type.equals("1")){   //添加好友
            int status = friendService.addFriend(userid,friendId);
            if(status==1){
                return new Result(true,StatusCode.OK,"关注成功");
            } else if(status == 2){
                return new Result(false,StatusCode.REPEATED,"不能重复关注");
            } else {
                return new Result(false,StatusCode.ERROR,"您已在对方黑名单中");
            }
        }
        if(type=="2"){   //删除好友

        }

        return new Result(false,StatusCode.ERROR,"出现错误");
    }
}
