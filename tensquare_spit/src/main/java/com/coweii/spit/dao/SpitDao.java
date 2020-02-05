package com.coweii.spit.dao;

import com.coweii.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpitDao extends MongoRepository<Spit,String> {
    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
