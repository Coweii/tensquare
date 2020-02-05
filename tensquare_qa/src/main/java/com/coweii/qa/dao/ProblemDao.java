package com.coweii.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.coweii.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * 某标签下的最新问答
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem AS p, tb_pl AS pl WHERE pl.labelid=? AND p.id=pl.`problemid` ORDER BY p.replytime DESC ", nativeQuery = true)
    Page<Problem> newlist(String labelid, Pageable pageable);  //分页

    /**
     * 某标签下的热门问答
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem AS p, tb_pl AS pl WHERE pl.labelid=? AND p.id=pl.`problemid` ORDER BY p.thumbup DESC ", nativeQuery = true)
    Page<Problem> hotlist(String labelid, Pageable pageable);

    /**
     * 某标签下的等待问答
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem AS p, tb_pl AS pl WHERE pl.labelid=? AND pl.`problemid` = p.`id` AND p.reply=0 ORDER BY p.createtime DESC  ", nativeQuery = true)
    Page<Problem> waitlist(String labelid, Pageable pageable);

}
