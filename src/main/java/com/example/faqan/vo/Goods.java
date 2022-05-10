/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.vo;

/**
 * @author xiaoyi.yang
 * @date 2021/11/30
 */
public class Goods {
    private int id;
    private String goodsName;
    private int qty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Goods(int id, String goodsName, int qty) {
        this.id = id;
        this.goodsName = goodsName;
        this.qty = qty;
    }

    public Goods() {
    }
}
