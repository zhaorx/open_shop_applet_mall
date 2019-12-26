package com.platform.service.shop.impl;

import com.platform.dao.shop.GoodsIssueDao;
import com.platform.entity.shop.GoodsIssueEntity;
import com.platform.service.shop.GoodsIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-23 14:12:34
 */
@Service("goodsIssueService")
public class GoodsIssueServiceImpl implements GoodsIssueService {
    @Autowired
    private GoodsIssueDao goodsIssueDao;

    @Override
    public GoodsIssueEntity queryObject(Integer id) {
        return goodsIssueDao.queryObject(id);
    }

    @Override
    public List<GoodsIssueEntity> queryList(Map<String, Object> map) {
        return goodsIssueDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return goodsIssueDao.queryTotal(map);
    }

    @Override
    public int save(GoodsIssueEntity goodsIssue) {
        return goodsIssueDao.save(goodsIssue);
    }

    @Override
    public int update(GoodsIssueEntity goodsIssue) {
        return goodsIssueDao.update(goodsIssue);
    }

    @Override
    public int delete(Integer id) {
        return goodsIssueDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return goodsIssueDao.deleteBatch(ids);
    }
}
