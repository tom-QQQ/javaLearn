package com.java.tests;

/**
 * 二分法数页数
 * @author Ning
 * @date Create in 2019/5/3
 */
public class A {

    public void countPage() {

        Integer pageIndex = 1;

        // 有数据页数
        int withDataPageNum = pageIndex;
        // 无数据页数
        int emptyPageNum;
        // 最后一页有数据的页数的数据量
        int lastWithDataPageSize;
        // 幂
        int power = 0;
        int changeTimes;
        // 索引是否需要增加, 有碰到为0的页数时将该值修改为false, 之后幂只能减少
        boolean isNeedAdd = true;



        int countTimes = 0;

        // 二分法数页数
        while (true){

            int currentPageSize = pageSize(pageIndex);
            if (currentPageSize < 5 && currentPageSize > 0) {
//                logger.info("[{}]页的数量为[{}], 判断该页为最后一页", pageIndex, currentPageSize);
                System.out.println(pageIndex + "页的数量小于5, 判断该页为最后一页");
                withDataPageNum = pageIndex;
                lastWithDataPageSize = currentPageSize;
                break;
            }

            countTimes++;

            if(currentPageSize == 0){

                emptyPageNum = pageIndex;
                isNeedAdd = false;

                if (emptyPageNum - withDataPageNum == 1) {
//                    logger.info("[{}]页的数量为5, [{}]无数据, 判断[{}]页为最后一页", withDataPageNum, pageIndex, withDataPageNum);
                    System.out.println(withDataPageNum + "页的数量为5, " + pageIndex + "无数据, 判断" + withDataPageNum + "页为最后一页");
                    lastWithDataPageSize = 5;
                    break;
                }

                power--;
                changeTimes = -1;
                pageIndex += pow(power) * changeTimes;

                continue;
            }

            changeTimes = 1;
            withDataPageNum = pageIndex;

            if (isNeedAdd) {
                power++;

            } else {
                power--;
            }

            pageIndex += pow(power) * changeTimes;
        }


        Integer newPageCount = withDataPageNum;
        Integer newRowCount = (withDataPageNum - 1)*5 + lastWithDataPageSize;

        System.out.println("总页数" + withDataPageNum + "最后一页数量" + lastWithDataPageSize);
        System.out.println("一共算了" + countTimes + "次");

    }

    private int pow(int a) {

        int result = 1;
        for (int i = 0; i < a; i++) {
            result *= 2;
        }
        return result;
    }

    int maxPage = 255;

    public int pageSize(int page) {

        if (page <= 0) {
            throw new RuntimeException("页数异常");
        }

        if (page < maxPage) {
            return 5;
        }

        if (page == maxPage) {
            return 5;
        }

        if (page > maxPage) {
            return 0;
        }

        throw new RuntimeException("页数异常");
    }
}
