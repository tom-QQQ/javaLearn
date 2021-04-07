package com.java.jvm.gc;

/**
 * GC时的自我拯救
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, I am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize method executed");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Exception {

        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;

        System.gc();

        Thread.sleep(500);

        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, I am dead!");
        }

        // 与上方代码相同, 但这次自我拯救失败, 因为一个对象的finalize()方法最多只会被系统自动调用一次
        SAVE_HOOK = null;

        System.gc();

        Thread.sleep(500);

        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, I am dead!");
        }
    }
}
