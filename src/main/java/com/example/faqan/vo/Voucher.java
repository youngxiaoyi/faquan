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
 * @date 2021/12/21
 */
public class Voucher {
    private int id;
    private String name;
    private int isCanUse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsCanUse() {
        return isCanUse;
    }

    public void setIsCanUse(int isCanUse) {
        this.isCanUse = isCanUse;
    }
}
