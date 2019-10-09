package com.java.skills.try_resource_catch;


/**
 * try-resource-catch语句的资源关闭实际上是在该语句执行完毕后调用java,long.AutoCloseable或java.io.Closeable的close()方法实现的
 * 即某个类实现以上任意一个接口的方法, 就可以利用try-resource-catch语句执行需要进行的操作
 * @author Ning
 * @date Create in 2019/4/26
 */
public class AutoCloseableImpl implements AutoCloseable {

    public void testMethod() {
        System.out.println("测试方法");
    }

    @Override
    public void close() throws Exception {
        System.out.println("调用了AutoCloseable接口的方法");
    }

    public static void main(String[] args) {


        try (AutoCloseableImpl autoCloseable = new AutoCloseableImpl()) {

            autoCloseable.testMethod();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
