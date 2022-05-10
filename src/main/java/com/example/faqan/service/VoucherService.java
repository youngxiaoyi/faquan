/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.service;

import com.example.faqan.mapper.VoucherMapper;
import com.example.faqan.vo.Voucher;
import com.example.faqan.vo.VoucherAndUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoyi.yang
 * @date 2021/12/21
 */
@Service
public class VoucherService implements IVoucherService {
    @Autowired
    VoucherMapper voucherMapper;

    @Override
    public List<Voucher> getAllVoucher() {
         return voucherMapper.getAllVoucher();
    }

    @Override
    public int createVoucherUser(VoucherAndUser voucherAndUser) {
        return voucherMapper.createVoucherUser(voucherAndUser);
    }
}
