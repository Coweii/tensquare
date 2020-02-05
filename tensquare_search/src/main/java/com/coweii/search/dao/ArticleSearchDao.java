package com.coweii.search.dao;

import com.coweii.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
    Page<Article> findByTitleOrContent(String title, String content, Pageable pageable);
}
