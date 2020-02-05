package com.coweii.spit.service;

import com.coweii.spit.dao.SpitDao;
import com.coweii.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.coweii.common.util.IdWorker;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpitService {

    @Autowired
    SpitDao spitDao;

    @Autowired
    IdWorker idWorker;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 根据父节点查询Spit
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentid, int page, int size){
        Pageable pageable = PageRequest.of(page-1,size);
        return spitDao.findByParentid(parentid,pageable);
    }

    /**
     * 点赞
     * @param spitId
     */
    public void thumbup(String spitId){
        //方式一：使用SpitDao两次数据库操作，效率较低
        //方式二
        // db.spit.update({"_id":"1"},{$inc:{thumbup:NumberInt(1)}})
        //限定条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        //更新操作
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }

    /**
     * 查询所有
     * @return
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    /**
     * 更新Spit
     * @param spit
     */
    public void updateSpit(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 添加Spit
     * @param spit
     */
    public void addSpit(Spit spit){
        //需要初始化
        spit.setid(idWorker.nextId()+"");  //ID
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态

        //存在父节点，父节点回复数加一
        if(spit.getParentid()!=null && !"".equals(spit.getParentid())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    /**
     * 根据Id删除
     * @param id
     */
    public void deleteSpitById(String id){
        spitDao.deleteById(id);
    }

}
