package com.coweii.qa.client;

import com.coweii.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("tensquare-base")
public interface BaseClient {
    @GetMapping("/label/{labelId}")
    Result getLabelById(@PathVariable("labelId") String labelId);
}
