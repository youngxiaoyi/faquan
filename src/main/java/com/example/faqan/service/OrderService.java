/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.service;

import com.example.faqan.mapper.GoodsMapper;
import com.example.faqan.mapper.OrderMapper;
import com.example.faqan.redis.RedisUtils;
import com.example.faqan.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoyi.yang
 * @date 2021/11/30
 */
@Service
public class OrderService implements  IOrderService{
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    RedisUtils redisUtils;
    @Override
    public boolean hasOrder(Order order) {
        return orderMapper.getUserGoods(order) >     0;
    }

    @Override
    @Transactional
    public boolean createOrder(Order order) {
        int result1 = goodsMapper.derQty(order.getGoodsId());
        if (result1<=0) {
            throw new RuntimeException();
        }
        int result2 = orderMapper.createOrder(order);
        if (result2<=0) {
            throw new RuntimeException();
        }
        Map map = new HashMap();
        map.put("userId", order.getUserId()+"");
        map.put("goodsId", order.getGoodsId()+"");
        redisUtils.hmset(order.getUserId() ,"user_order", map, 0);
        return true;
    }
}
