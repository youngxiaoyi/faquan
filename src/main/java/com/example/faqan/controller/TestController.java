/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.example.faqan.controller;

import com.example.faqan.config.RedissonConfig;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description 算法controller
 * @Author xiaoyi.yang
 * @Data 2022/2/9 14:22
 */
public class TestController {
    /**
     * 链表数据结构
     */
    public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }



    /**
     * 两数之和
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> cache = new HashMap();
        for (int i = 0; i < nums.length - 1; i++) {
            if (cache.containsKey(nums[i])) {
                return new int[]{cache.get(nums[i]),i};
            } else {
                cache.put(target-nums[i],i);
            }
        }
        return result;
    }

    /**
     * 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode result1 = result;
        int temp = 0;
        while (l1 != null || l2 != null || temp !=0) {
            int value = temp;
            temp = 0;
            if (l1!=null) {
                value += l1.val;
                l1 = l1.next;
            }
            if (l2!=null) {
                value += l2.val;
                l2 = l2.next;
            }
            if (value>=10) {
                temp = value / 10;
                value = value % 10;
            }
            ListNode item = new ListNode(value);
            result.next = item;
            result = result.next;
        }
        return result1.next;
    }

    /**
     * 无重复字符的最长子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                left = Math.max(left, map.get(chars[i])+1);
            }
            map.put(chars[i], i);
            max = Math.max(max, i-left+1);
        }
        return max;
    }

    /**
     * 寻找两个正序数组的中位数（寻找第k个数）
     * @param nums1
     * @param nums2
     * @return
     */
//    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int k = (nums1.length + nums2.length) / 2;
//        int half = k / 2;
//        if (nums1[half-1] < nums2[half-1]) {
//            int[] nums11 = new int[nums1.length];
//            System.arraycopy(nums1, half-1, nums11, 0, nums1.length-half+1);
//            findMedianSortedArrays(nums11, nums2);
//        } else {
//            int[] nums22 = new int[nums2.length];
//            System.arraycopy(nums2, half-1, nums22, 0, nums2.length-half+1);
//            findMedianSortedArrays(nums1, nums22);
//        }
//
//    }
    @Resource
    private RedissonConfig redissonConf;

    /**
     * 主测试函数
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = new int[]{2,3,8,222,7,11,15};
//        int target = 9;
//        int[] res = twoSum(nums, target);
//
//        ListNode listNode1 = new ListNode(3);
//        ListNode listNode2 = new ListNode(4,listNode1);
//        ListNode listNode3 = new ListNode(2,listNode2);
//        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(6,listNode4);
//        ListNode listNode6 = new ListNode(5,listNode5);
//        ListNode listNode7 = new ListNode(5,listNode6);
//        ListNode res = addTwoNumbers(listNode3, listNode7);
//        String s = "bbbbb";
//        int res = lengthOfLongestSubstring(s);
//        int[] nums1 = new int[]{1,3};
//        int[] nums2 = new int[]{2};
//        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println("------");
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("xioayi");
        threadLocal.get();
        ArrayList s = new ArrayList();
        HashMap hashMap = new HashMap();
        ConcurrentMap concurrentMap = null;
        Executors executors;

    }
    public  void test () throws InterruptedException {
        RedissonClient redissonClient = redissonConf.getRedissonClient();
        RLock yxy = redissonClient.getLock("yxy");
        yxy.tryLock();
        yxy.tryLock(123123L,TimeUnit.SECONDS);
        yxy.tryLock(123123L, 123123L, TimeUnit.SECONDS);
        yxy.unlock();
        RLock yxy1 = redissonClient.getMultiLock(yxy,yxy,yxy);
        yxy1.lock();
    }

}
