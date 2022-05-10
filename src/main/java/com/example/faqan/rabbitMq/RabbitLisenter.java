/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.rabbitMq;

import com.example.faqan.redis.RedisUtils;
import com.example.faqan.service.OrderService;
import com.example.faqan.vo.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiaoyi.yang
 * @date 2021/12/1
 */
@Component
public class RabbitLisenter {

    @Autowired
    OrderService orderService;

    @Autowired
    RedisUtils redisUtils;



    @RabbitListener(queues = RabbitSender.MIAOSHA_QUEUE)
    public void receive(String message) {
        Message messages = RabbitSender.stringToBean(message, Message.class);
        System.out.println("接受到的消息"+messages+ "time:" + System.currentTimeMillis());

        Order order = new Order(messages.getUserId(), messages.getGoodsId());

        orderService.createOrder(order);
    }
}
