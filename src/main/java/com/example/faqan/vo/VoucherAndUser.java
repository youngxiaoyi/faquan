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
 * @date 2021/12/22
 */
public class VoucherAndUser {
    private int voucherId;
    private int userId;

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public VoucherAndUser(int voucherId, int userId) {
        this.voucherId = voucherId;
        this.userId = userId;
    }

    public VoucherAndUser() {
    }
}
