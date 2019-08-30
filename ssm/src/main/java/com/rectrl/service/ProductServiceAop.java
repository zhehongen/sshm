package com.rectrl.service;

import com.rectrl.entity.Product;
import com.rectrl.security.AdminOnly;
import org.springframework.stereotype.Service;


/**
 * @author zc
 * @version 1.0 2017-09-03
 * @title 产品服务类
 * @describe 产品相关业务服务-AOP方式实现权限校验
 */
@Service
public class ProductServiceAop {

    @AdminOnly
    public void insert(Product product) {
        System.out.println("insert product");
    }

    @AdminOnly
    public void delete(Long id) {
        System.out.println("delete product");
    }

}
