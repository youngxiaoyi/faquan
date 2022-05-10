/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.controller;

import com.example.faqan.rabbitMq.Message;
import com.example.faqan.rabbitMq.RabbitSender;
import com.example.faqan.redis.RedisUtils;
import com.example.faqan.service.GoodsService;
import com.example.faqan.service.OrderService;
import com.example.faqan.vo.Goods;
import com.example.faqan.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaoyi.yang
 * @date 2021/11/30
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    OrderService orderService;

    @Autowired
    RabbitSender rabbitSender;
    /**
     * redis key  :
     * 商品库存：商品id + _num_good
     * 用户秒杀订单： 用户id + user_order
     * 用户秒杀标记： 用户id + 商品id + _miaosha
     *
     */

    /**
     * 库存初始化
     */
    @RequestMapping(value = "/init")
    public void goodsInit(){
        List<Goods> allGoods = goodsService.getAllGoods();
        for (Goods good: allGoods) {
            redisUtils.set(good.getId(), "_num_good", good.getQty(), 0);
            System.out.println(good.getId()+"商品初始化redis");
        }
    }

    /**
     * 秒杀
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/miaosha")
    public String qiang(@RequestBody Order order){

        String  hasMiaosha = redisUtils.get(order.getUserId()+""+ order.getGoodsId()+"_miaosha");
        if ("1".equals(hasMiaosha)) {
            System.out.println("你已有秒杀活动存在，请等待");
        }
        List<String> hasOrder = redisUtils.hmget(order.getUserId() + "user_order", "userId");
        if (hasOrder.get(0) != null && !"".equals(hasOrder.get(0))){
            return order.getGoodsId()+"你已经秒杀成功，可查看订单信息";
        }
        Long stock = redisUtils.decr(order.getGoodsId(), "_num_good");
        if (stock < 0L) {
            redisUtils.incr(order.getGoodsId(), "_num_good");
            return order.getGoodsId()+"已被秒杀完";
        }
        redisUtils.set(order.getUserId() , order.getGoodsId()+ "_miaosha", 1, 0);
        rabbitSender.sendMessage(new Message(order.getUserId(), order.getGoodsId()));

        return "成功秒杀!!!";
    }
}
