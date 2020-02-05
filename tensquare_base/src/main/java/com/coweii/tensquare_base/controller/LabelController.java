package com.coweii.tensquare_base.controller;

import com.coweii.tensquare_base.polo.Label;
import com.coweii.tensquare_base.service.LabelService;
import com.coweii.common.entity.Result;
import com.coweii.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    LabelService labelService;

    /*
    获取所有标签
     */
    @GetMapping
    public Result<List<Label>> getAllLabels(){
        return new Result<>(true, StatusCode.OK, "查找成功", labelService.findAll());
    }

    /*
    添加标签
     */
    @PostMapping
    public Result addLabel(@RequestBody Label label){
        labelService.add(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /*
    根据Id查询标签
     */
    @GetMapping("/{labelId}")
    public Result<Label> getLabelById(@PathVariable("labelId") String id){
        System.out.println("3333333333");
        return new Result<>(true,StatusCode.OK,"查找成功",labelService.findById(id));
    }

    /*
    根据Id和Label修改标签
     */
    @PutMapping("/{id}")
    public Result updateLabel(@PathVariable String id, @RequestBody Label label){
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /*
    根据Id删除标签
     */
    @DeleteMapping("/{labelId}")
    public Result deleteLabelById(@PathVariable("labelId") String id){
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 条件查询
     * @param label  封装查询条件
     * @return
     */
    @PostMapping("/search")
    public Result<List<Label>> findSearch(@RequestBody Label label){
        return new Result<>(true,StatusCode.OK,"查找成功",labelService.findSearch(label));
    }
}
