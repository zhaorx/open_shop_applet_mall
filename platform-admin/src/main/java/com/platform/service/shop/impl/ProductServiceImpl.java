package com.platform.service.shop.impl;

import com.platform.dao.shop.GoodsSpecificationDao;
import com.platform.dao.shop.ProductDao;
import com.platform.entity.shop.GoodsSpecificationEntity;
import com.platform.entity.shop.ProductEntity;
import com.platform.service.shop.ProductService;
import com.platform.utils.BeanUtils;
import com.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-30 14:31:21
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private GoodsSpecificationDao goodsSpecificationDao;

    @Override
    public ProductEntity queryObject(Integer id) {
        return productDao.queryObject(id);
    }

    @Override
    public List<ProductEntity> queryList(Map<String, Object> map) {
        List<ProductEntity> list = productDao.queryList(map);

        List<ProductEntity> result = new ArrayList<>();
        //翻译产品规格
        if (null != list && list.size() > 0) {
            for (ProductEntity item : list) {
                String specificationIds = item.getGoodsSpecificationIds();
                String specificationValue = "";
                if (!StringUtils.isNullOrEmpty(specificationIds)) {
                    String[] arr = specificationIds.split("_");

                    for (String goodsSpecificationId : arr) {
                        GoodsSpecificationEntity entity = goodsSpecificationDao.queryObject(goodsSpecificationId);
                        if (null != entity) {
                            specificationValue += entity.getValue() + "；";
                        }
                    }
                }
                item.setSpecificationValue(item.getGoodsName() + " " + specificationValue);
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return productDao.queryTotal(map);
    }

    @Override
    @Transactional
    public int save(ProductEntity product) {
        int result = 0;
        String goodsSpecificationIds = product.getGoodsSpecificationIds();
        if (!StringUtils.isNullOrEmpty(goodsSpecificationIds)) {
            String[] goodsSpecificationIdArr = goodsSpecificationIds.split("_");
            if(goodsSpecificationIdArr.length==2) {
            	 for (int i = 0; i < goodsSpecificationIdArr.length - 1; i++) {
                     String[] oneId = goodsSpecificationIdArr[i].split(",");
                     String[] twoId = goodsSpecificationIdArr[i + 1].split(",");
                     for (int j = 0; j < oneId.length; j++) {
                         for (int k = 0; k < twoId.length; k++) {
                             String strGoodsSpecificationIds = null;
                             if (StringUtils.isNullOrEmpty(oneId[j]) || StringUtils.isNullOrEmpty(twoId[k])){
                                 continue;
                             }
                             strGoodsSpecificationIds = oneId[j] + "_" + twoId[k];
                             product.setGoodsSpecificationIds(strGoodsSpecificationIds);
                             ProductEntity entity = new ProductEntity();
                             BeanUtils.copyProperties(product, entity);
                             result += productDao.save(entity);
                         }
                     }
                 }
            }else if(goodsSpecificationIdArr.length==1) {
            	String goodsSpecificationId[]=goodsSpecificationIdArr[0].split(",");
            	for (int i = 0; i < goodsSpecificationId.length; i++) {
					String string = goodsSpecificationId[i];
					 product.setGoodsSpecificationIds(string);
                     ProductEntity entity = new ProductEntity();
                     BeanUtils.copyProperties(product, entity);
                     result += productDao.save(entity);
				}
            }

        }
        return result;
    }

    @Override
    public int update(ProductEntity product) {
        if (StringUtils.isNullOrEmpty(product.getGoodsSpecificationIds())){
            product.setGoodsSpecificationIds("");
        }
        return productDao.update(product);
    }

    @Override
    public int delete(Integer id) {
        return productDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return productDao.deleteBatch(ids);
    }
}
