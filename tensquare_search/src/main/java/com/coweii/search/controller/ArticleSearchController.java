package com.coweii.search.controller;

import com.coweii.search.pojo.Article;
import com.coweii.search.service.ArticleSearchService;
import com.coweii.common.entity.PageResult;
import com.coweii.common.entity.Result;
import com.coweii.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleSearchController {

    @Autowired
    ArticleSearchService articleSearchService;

    @PostMapping
    public Result<Article> addArticle(@RequestBody Article article){
        return new Result<>(true, StatusCode.OK,"ES添加成功",articleSearchService.addArticle(article));
    }

    @GetMapping("/search/{keywords}/{page}/{size}")
    public Result<PageResult<Article>> searchArticleByTitileOrContent(@PathVariable String keywords, @PathVariable int page, @PathVariable int size){
        Page p = articleSearchService.searchArticleByTitleOrContent(keywords,keywords,page,size);
        return new Result<>(true,StatusCode.OK,"搜索成功",new PageResult<>(p.getTotalElements(),p.getContent()));
    }
}
