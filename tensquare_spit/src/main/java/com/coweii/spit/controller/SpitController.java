package com.coweii.spit.controller;

import com.coweii.spit.pojo.Spit;
import com.coweii.spit.service.SpitService;
import com.coweii.common.entity.PageResult;
import com.coweii.common.entity.Result;
import com.coweii.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    SpitService spitService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping
    public Result<List<Spit>> findAll(){
        return new Result<>(true,StatusCode.OK,"查询所有成功",spitService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Spit> findById(@PathVariable String id){
        return new Result<>(true,StatusCode.OK,"根据Id查询成功",spitService.findById(id));
    }

    @GetMapping("/{parentid}/{page}/{size}")
    public Result<PageResult<Spit>> findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        Page<Spit> page1 = spitService.findByParentid(parentid,page,size);
        return new Result<>(true,StatusCode.OK,"根据父ID查询成功",new PageResult<>(page1.getTotalElements(),page1.getContent()));
    }

    @PutMapping("/{id}")
    public Result updateSpit(@PathVariable String id, @RequestBody Spit spit){
        spit.setid(id);
        spitService.updateSpit(spit);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId){
        String userId = "111";  //暂时没做登录，先写死用户ID
        if(redisTemplate.opsForValue().get(userId+"_"+spitId) != null){
            return new Result(false,StatusCode.REPEATED,"不能重复点赞");
        }
        spitService.thumbup(spitId);
        return new Result(true,StatusCode.OK,"点赞成功");
    }

    @PostMapping
    public Result addSpit(@RequestBody Spit spit){

        spitService.addSpit(spit);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteSpit(@PathVariable String id){
        spitService.deleteSpitById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
