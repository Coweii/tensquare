package com.coweii.search.service;

import com.coweii.search.dao.ArticleSearchDao;
import com.coweii.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleSearchService {
    @Autowired
    ArticleSearchDao articleSearchDao;

    public Article addArticle(Article article){
        return articleSearchDao.save(article);
    }

    public Page<Article> searchArticleByTitleOrContent(String title, String content, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return articleSearchDao.findByTitleOrContent(title,content,pageable);
    }
}
