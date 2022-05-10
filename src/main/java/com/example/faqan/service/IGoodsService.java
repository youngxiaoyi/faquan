/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.service;

import com.example.faqan.vo.Goods;

import java.util.List;

/**
 * @author xiaoyi.yang
 * @date 2021/11/30
 */
public interface IGoodsService {
    List<Goods> getAllGoods ();
}
