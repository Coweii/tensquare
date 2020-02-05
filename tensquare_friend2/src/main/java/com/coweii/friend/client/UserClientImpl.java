package com.coweii.friend.client;

import com.coweii.common.entity.Result;
import com.coweii.common.entity.StatusCode;
import org.springframework.stereotype.Component;

//熔断器回调
@Component
public class UserClientImpl implements UserClient {
    @Override
    public Result updateFansCount(String id, int x) {
        return new Result(false, StatusCode.ERROR,"熔断器启动");
    }

    @Override
    public Result updateFollowCount(String id, int x) {
        return new Result(false, StatusCode.ERROR,"熔断器启动");
    }
}
