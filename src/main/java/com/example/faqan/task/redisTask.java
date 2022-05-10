/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.task;

import com.example.faqan.redis.RedisUtils;
import com.example.faqan.service.VoucherService;
import com.example.faqan.vo.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiaoyi.yang
 * @date 2021/12/21
 */
@Component
public class redisTask {
    @Autowired
    VoucherService voucherService;
    @Autowired
    RedisUtils redisUtils;


    //每10秒加载10条数据到缓存队列中
    @Scheduled(cron = "0/10 * * * * ?")
    public void getVoucherToRedis () {
        System.out.println("定时任务起始时间："+System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            String item = redisUtils.rPopString("allVoucher");
            if (item != null && !"".equals(item)) {
                System.out.println("定时任务："+System.currentTimeMillis());
                redisUtils.lpushString("voucher", item);
            }
        }
        System.out.println("定时任务结束时间："+System.currentTimeMillis());
    }
}
