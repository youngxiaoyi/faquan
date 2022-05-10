/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.mapper;

import com.example.faqan.vo.Voucher;
import com.example.faqan.vo.VoucherAndUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoyi.yang
 * @date 2021/12/21
 */
@Mapper
public interface VoucherMapper {
    List<Voucher> getAllVoucher();

    int createVoucherUser(@Param("voucherAndUser")VoucherAndUser voucherAndUser);
}
