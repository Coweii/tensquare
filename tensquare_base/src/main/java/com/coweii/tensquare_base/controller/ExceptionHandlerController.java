package com.coweii.tensquare_base.controller;

import com.coweii.common.entity.Result;
import com.coweii.common.entity.StatusCode;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public Result error(Exception e){
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }

}
