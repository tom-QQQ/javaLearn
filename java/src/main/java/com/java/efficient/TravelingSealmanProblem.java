package com.java.efficient;

/**
 * @author Ning
 * @date Create in 2019/8/1
 */
public class TravelingSealmanProblem {

    private static Point[] pointPositions;

    static {
        pointPositions = new Point[8];
        addPoint(0, 5.0, 6.0, "Super");
        addPoint(1, 2.0, 7.0, "B");
        addPoint(2, 10.0, 5.0, "C");
        addPoint(3, 0.0, 4.0, "D");
        addPoint(4, 6.0, 3.0, "E");
        addPoint(5, 9.0, 5.0, "F");
        addPoint(6, 2.0, 10.0, "G");
        addPoint(7, 5.0, 9.0, "H");
    }

    static class Point {

        private double xCoordinate;
        private double yCoordinate;
        private String name;

        Point(double xCoordinate, double yCoordinate, String name) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
            this.name = name;
        }

        double getXCoordinate() {
            return xCoordinate;
        }

        double getYCoordinate() {
            return yCoordinate;
        }

        String getName() {
            return name;
        }
    }

    public static void dealProblem() {

        System.out.println("First city is Super");
        int currentPointIndex = 0;

        // 当只剩一个点时循环即可结束
        while (currentPointIndex < pointPositions.length - 1){

            // 初始点设置为0
            int minPointIndex = 0;
            double minDistance = Double.MAX_VALUE;

            // 每次和下一个点及其之后的点进行比较
            for (int index = currentPointIndex + 1; index < pointPositions.length; index++) {

                // x坐标的差的平方大于minDistance时, 就可以少计算一次计算y差的平方和了
                double distance = square(pointPositions[currentPointIndex].getXCoordinate() - pointPositions[index].getXCoordinate());
                if (distance > minDistance) {
                    continue;
                }

                // 之前已经计算出x坐标的差平方了, 就直接和y坐标差的平方和相加得到欧几里得距离的平方
                distance = calculateDistance(distance, pointPositions[currentPointIndex], pointPositions[index]);

                if (distance < minDistance) {
                    minDistance = distance;
                    minPointIndex = index;
                }
            }

            System.out.println("next city is " + pointPositions[minPointIndex].getName() + ", distance square is " + minDistance);
            // ***将最近距离的点交换到目标点之后的位置, 之后循环只用循环未访问过的点***
            swapPoint(currentPointIndex + 1, minPointIndex);
            currentPointIndex++;
        }
    }

    private static void addPoint(int index, double xPosition, double yPosition, String name) {

        pointPositions[index] = new Point(xPosition, yPosition, name);
    }

    private static double calculateDistance(double xDifference, Point aPosition, Point bPosition) {

        // ***使用欧几里得距离进行比较时, 由于只进行大小的比较, 比较差的平方和即可, 不需要再计算开方了, 可以减少数据的计算量****
        return xDifference + square(aPosition.getYCoordinate() - bPosition.getYCoordinate());
    }

    private static double square(double value) {
        return Math.pow(value, 2);
    }

    /**
     * 交换2个点
     * @param a a
     * @param b b
     */
    private static void swapPoint(int a, int b) {

        Point aPoint = pointPositions[a];
        pointPositions[a] = pointPositions[b];
        pointPositions[b] = aPoint;

    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        TravelingSealmanProblem.dealProblem();
        System.out.println("耗时: " + (System.currentTimeMillis() - startTime) + "毫秒");
    }
}
