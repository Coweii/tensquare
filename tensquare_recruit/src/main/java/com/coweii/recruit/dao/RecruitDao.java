package com.coweii.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.coweii.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    /**
     * 查询最新的6个推荐职位
     * @param state  "0"表示关闭，"1"表示开启，"2"表示推荐
     * @return
     */
	List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state); // where state = ? order by createtime

    /**
     * 查询最新的6个职位
     * @param state   "0"表示关闭，"1"表示开启，"2"表示推荐
     * @return
     */
    List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);  // where state != ? order by createtime desc;
}
