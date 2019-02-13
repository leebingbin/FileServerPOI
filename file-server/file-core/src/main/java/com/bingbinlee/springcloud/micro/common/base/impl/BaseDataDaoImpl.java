package com.bingbinlee.springcloud.micro.common.base.impl;

import com.bingbinlee.springcloud.micro.common.base.BaseDataDao;
import com.bingbinlee.springcloud.micro.common.base.model.BaseDataModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

@Component("baseDataDao")
public class BaseDataDaoImpl<T extends BaseDataModel> implements BaseDataDao<T> {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(T t) throws Exception {
        mongoTemplate.insert(t);
    }

    @Override
    public void insertAll(List<T> tList) throws Exception {
        mongoTemplate.insertAll(tList);
    }

    @Override
    public void update(T t) throws Exception {
        mongoTemplate.save(t);
    }

    @Override
    public void deleteById(String id, Class<T> clazz) throws Exception {
        mongoTemplate.findAndRemove(new Query(Criteria.where("_id").is(id)), clazz);
    }

    @Override
    public void deleteEntity(T t) throws Exception {
        mongoTemplate.remove(t);
    }

    @Override
    public T selectById(String id, Class<T> clazz) throws Exception {
        return (T) mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), clazz);
    }

    @Override
    public List<T> getAll(Class<T> clazz) throws Exception {
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "ctime");
        List<T> list = mongoTemplate.findAll(clazz);
        return list;
    }


    @Override
    public int isRepeat(String isRepeatAllFieldValue, Class<T> clazz) throws Exception {
        Query query = new Query();
        Criteria c = new Criteria();
        Criteria c2 = new Criteria();
        c2 = Criteria.where("md5Value").is(isRepeatAllFieldValue);
        c.andOperator(c2);
        query.addCriteria(c);
        return (int) mongoTemplate.count(query, clazz);
    }

    @Override
    public List<T> selectChildrenById(String id, Class<T> clazz) throws Exception {
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "status");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC, "ctime");
        Sort sort = new Sort(order1, order2);
        List<T> ret = mongoTemplate.find(new Query(Criteria.where("pId").is(id)).with(sort), clazz);
        return ret;
    }

    public void updateDataStatuById(Map<Object, Object> map, Class<T> clazz) throws Exception {
        Update update = new Update();
        Query query = new Query();
        Criteria c = new Criteria();
        for (Object key : map.keySet()) {
            if ("Id".equalsIgnoreCase(key.toString())) {
                c = Criteria.where("_id").is(map.get(key));
            } else {
                //主要修改 enable status 两个字段
                update.set(key.toString(), map.get(key));
            }
        }
        query.addCriteria(c);
        mongoTemplate.updateMulti(query, update, clazz);
    }

    public long getGroupCount(Map<Object, Object> map, Class<T> clazz) {
        Long total = 0l;
        String factoryId = "";
        for (Object key : map.keySet()) {
            if ("factoryId".equalsIgnoreCase(key.toString())) {
                factoryId = map.get(key).toString();
            }
        }
        //统计订单表数据  根据厂商ID 统计该厂商下的每种商品的 销售总额 按照商品ID分组查询
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("factoryId").is(factoryId)),
                group("productId").sum("ptotalMoney").as("total")
        );

        AggregationResults<T> ar = mongoTemplate.aggregate(aggregation, "t_order", clazz);
        List<T> list = ar.getMappedResults();
        if (list.size() > 0) {
            total = list.size() + 0l;
        }
        return total;
    }
}
