package com.iolo.redpacket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by FengXinXin on 2018/4/23.
 */
public class RedPacket {
    public static void main(String[] args) {
        double totalAmout = 0;
        List<Integer> amountList = divideRedPacket(5000, 30);
        for (Integer amount : amountList) {
            totalAmout += new BigDecimal(amount).doubleValue();
            System.out.println("抢到金额： "+new BigDecimal(amount));
        }
        System.out.println("总金额： " + totalAmout);
    }

    /**
     * 二倍均值法
     * 随机区间（0，M/N x 2）
     * 假设有10个人，红包总额100元。
     * 100/10X2 = 20, 所以第一个人的随机范围是（0，20 )，平均可以抢到10元。
     * 假设第一个人随机到10元，那么剩余金额是100-10 = 90 元。
     * 90/9X2 = 20, 所以第二个人的随机范围同样是（0，20 )，平均可以抢到10元。
     * 假设第二个人随机到10元，那么剩余金额是90-10 = 80 元。
     * 80/8X2 = 20, 所以第三个人的随机范围同样是（0，20 )，平均可以抢到10元。
     * 以此类推，每一次随机范围的均值是相等的。
     * 文章链接：https://mp.weixin.qq.com/s/AIE33sdT2QI6UL8cs1kJCQ
     * @param totalAmount
     * @param totalPeopleNum
     * @return
     */
    public static List<Integer> divideRedPacket(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return  amountList;
    }
}
