package com.java.tests;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Ning
 * @date Create in 2019/5/3
 */
public class TestClass {

    public static void main(String[] args) throws Exception {

//        long amount = 912152702068098072L;
//        double feeRate = 1.2;
//        System.out.println(String.format("商户收款金额%d", amount));
//        long totalAmount = calculateTotalAmount(amount, feeRate);
//        System.out.println(String.format("目标金额为%d", totalAmount));
//        System.out.println(String.format("计算手续费后的商户收款金额%d", feeCalculate(totalAmount, feeRate)));

        test_();

    }

    private static long feeCalculate(Long transAmt, double rate) {

        return transAmt - new BigDecimal(String.valueOf(transAmt)).multiply(BigDecimal.valueOf(rate).
                divide(new BigDecimal("100"),10,BigDecimal.ROUND_HALF_UP))
                .setScale(0,BigDecimal.ROUND_HALF_UP).longValue();

    }

    private static long calculateTotalAmount(Long transAmt, double rate) {

        BigDecimal withoutFeePercentage = new BigDecimal(1.0).subtract((BigDecimal.valueOf(rate).
                divide(new BigDecimal("100"),10,BigDecimal.ROUND_HALF_UP)));
        return new BigDecimal(String.valueOf(transAmt)).divide(withoutFeePercentage, 0, BigDecimal.ROUND_HALF_UP).longValue();
    }

    private static void test() {

        long totalCount = 0L;
        long faultCount = 0L;
        for (int times = 0; times < 1000000000; times++) {

            long amount = times;

            double feeRate = 15.2;

            long actualAmount = feeCalculate(calculateTotalAmount(amount, feeRate), feeRate);
            if (actualAmount < 0) {
                continue;
            }

            totalCount++;
            if (amount != actualAmount) {
                System.out.println(String.format("费率计算错误, 收款金额%d, 实际收款金额%d", amount, actualAmount));
                faultCount++;
            }
        }

        System.out.println(String.format("总共测试了%d次, 错误%d次, 错误率%f", totalCount, faultCount,
                BigDecimal.valueOf(faultCount).divide(BigDecimal.valueOf(totalCount), 5, BigDecimal.ROUND_HALF_UP).doubleValue()));

    }

    private static void test_() {

        Random random = new Random();

        int size = 100000000;
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {

            list.add(random.nextInt());
        }

        long time = System.currentTimeMillis();
        for (int i = 0; i < size - 1; i++) {

            TestClass.absoult_(list.get(i), list.get(i + 1));
        }
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();

        for (int i = 0; i < size - 1; i++) {
            TestClass.absoult(list.get(i), list.get(i + 1));
        }
        System.out.println(System.currentTimeMillis() - time);
    }

    private static void absoult(int a, int b) {

        int c = a;
        a = b;
        b = c;
    }

    private static void absoult_(int a, int b) {

        a ^= b;
        b ^= a;
        a ^= b;
    }
}
