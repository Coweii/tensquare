package com.coweii.friend.client;

import com.coweii.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

//fallback 表示熔断器回调
@FeignClient(value="tensquare-user", fallback = UserClientImpl.class)
public interface UserClient {
    /**
     * 更新粉丝数
     * @param id
     * @param x
     * @return
     */
    @PutMapping("/user/fans/{id}/{x}")
    Result updateFansCount(@PathVariable String id, @PathVariable int x);

    /**
     * 更新关注数
     * @param id
     * @param x
     * @return
     */
    @PutMapping("/user/follow/{id}/{x}")
    Result updateFollowCount(@PathVariable String id, @PathVariable int x);
}
