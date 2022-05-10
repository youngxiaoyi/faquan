/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.rabbitMq;

/**
 * @author xiaoyi.yang
 * @date 2021/11/30
 */
public class Message {
    private int userId;
    private int goodsId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public Message(int userId, int goodsId) {
        this.userId = userId;
        this.goodsId = goodsId;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "userId=" + userId +
                ", goodsId=" + goodsId +
                '}';
    }
}
