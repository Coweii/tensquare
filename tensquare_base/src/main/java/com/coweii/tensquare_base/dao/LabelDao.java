package com.coweii.tensquare_base.dao;

import com.coweii.tensquare_base.polo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface LabelDao extends JpaRepository<Label,String>,
        JpaSpecificationExecutor<Label> {
    //JpaRepository提供了基本的增删改查
    //JpaSpecificationExecutor用于做复杂的条件查询
}
