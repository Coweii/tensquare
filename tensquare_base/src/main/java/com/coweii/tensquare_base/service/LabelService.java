package com.coweii.tensquare_base.service;

import com.coweii.tensquare_base.dao.LabelDao;
import com.coweii.tensquare_base.polo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.coweii.common.util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {

    @Autowired
    LabelDao labelDao;

    @Autowired
    IdWorker idWorker;

    /*
    查询所有标签
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /*
    根据Id查询Label
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /*
    修改Label
     */
    public void update(Label label){
        labelDao.save(label);
    }

    /*
    添加Label
     */
    public void add(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /*
    根据Id删除Label
     */
    public void deleteById(String Id){
        labelDao.deleteById(Id);
    }


    /**
     *
     * @param label  封装查询条件
     * @return
     */
    private Specification<Label> createSpecification(Label label){
        return new Specification<Label>() {
            /**
             *
             * @param root  根对象，也就是要把条件封装到哪个对象中
             * @param cq  criteriaQuery  封装查询关键字 group by order by 等，一般不会用到
             * @param cb  criteriaBuilder   用来封装条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //return null; 表示无条件

                List<Predicate> predicateList = new ArrayList<>();

                if(label.getLabelname() != null && !"".equals(label.getLabelname())){
                    predicateList.add(
                            //  labelname like %java%
                            cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%")
                    );
                }
                if(label.getState() != null && !"".equals(label.getState())){
                    predicateList.add(
                            //  state = 1
                            cb.equal(root.get("state").as(String.class),label.getState())
                    );
                }

                Predicate[] predicates = new Predicate[predicateList.size()];
                //  where labelname like %java% and state = 1
                return cb.and(predicateList.toArray(predicates));
            }
        };
    }

    /**
     * 条件查询
     * @param label 封装查询条件
     * @return
     */
    public List<Label> findSearch(Label label){
        return labelDao.findAll(createSpecification(label));
    }


}
