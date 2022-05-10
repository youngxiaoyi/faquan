/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.controller;

import com.example.faqan.redis.RedisUtils;
import com.example.faqan.service.VoucherService;
import com.example.faqan.vo.User;
import com.example.faqan.vo.Voucher;
import com.example.faqan.vo.VoucherAndUser;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * @author xiaoyi.yang
 * @date 2021/12/1
 */
@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    RedisUtils redisUtils;



    @Autowired
    VoucherService voucherService;

    @RequestMapping("init")
    public void init () {
//        try{
            List<Voucher> allVoucher = voucherService.getAllVoucher();
            for (Voucher voucher:allVoucher) {
                redisUtils.lpushString("allVoucher", String.valueOf(voucher.getId()));
            }
//        } catch (Exception e){
//            System.out.println("失败");
//        }
        System.out.println("成功");
    }

    @RequestMapping("/getVoucher")
    public String getVoucherUser (@RequestBody User user) {
        int id = user.getId();
        String val = redisUtils.get(id+"_voucher");
        if (val!=null && "1".equals(val)) {
            return "你已经领取过";
        }
        String voucherId = redisUtils.rPopString("voucher");
        if (voucherId!=null && !"".equals(voucherId)) {
            redisUtils.set(id, "_voucher", "1", 0);
        }else {
            System.out.println("用户:"+id+"已经领取完了，稍后再来");
            return "领取失败";
        }
        VoucherAndUser voucherAndUser = new VoucherAndUser(Integer.valueOf(voucherId), id);
        int result = voucherService.createVoucherUser(voucherAndUser);
        if (result>=0) {
            System.out.println("用户:"+id+"领取成功");
            return "领取成功";
        }
        System.out.println("用户:"+id+"领取失败");
        return "领取失败";
    }

}
