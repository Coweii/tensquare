package com.coweii.article.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.coweii.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 文章审核通过，state设为1
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE tb_article SET state='1' WHERE id=?" , nativeQuery = true)
    Article updateArticle(String id);

    /**
     * 初始化点赞数为0
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup=0 WHERE id=?;", nativeQuery = true)
    void setThumbupZero(String id);

    /**
     * 点赞
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup=thumbup+1 WHERE id=?;", nativeQuery = true)
    Article addThumbup(String id);
}
