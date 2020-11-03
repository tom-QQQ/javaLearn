package com.java.on.java8.function.program.compare;

interface Strategy {
    String approach(String msg);
}

class Soft implements Strategy {

    @Override
    public String approach(String msg) {
        return msg.toLowerCase().concat("?");
    }
}

class Unrelated {
    static String twice(String msg) {
        return msg + " " + msg;
    }
}

public class Strategize {

    Strategy strategy;
    String msg;

    Strategize(String msg) {
        strategy = new Soft();
        this.msg = msg;
    }

    void communicate() {
        System.out.println(strategy.approach(msg));
    }

    void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public static void main(String[] args) {

        Strategy[] strategies = {
                // 重写approach方法实现不同功能
                new Strategy() { // [2]
                    public String approach(String msg) {
                        return msg.toUpperCase() + "!";
                    }
                },
                // 使用函数式表达式简化写法, 只写出最重要的部分, 参数和处理行为
                msg -> msg.substring(0, 5),
                // 使用方法引用, ::左边是类或对象名称, 右边是方法名称, 无参数列表
                Unrelated::twice
        };

        Strategize s = new Strategize("Hello there");
        s.communicate();
        for (Strategy newStrategy : strategies) {
            // 传入不同执行策略
            s.changeStrategy(newStrategy);
            s.communicate();
        }
    }
}

