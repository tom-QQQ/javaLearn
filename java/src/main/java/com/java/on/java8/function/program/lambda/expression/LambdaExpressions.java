package com.java.on.java8.function.program.lambda.expression;

interface Description {
    String brief();
}

interface Body {
    String detailed(String head);
}

interface Multi {
    String twoArg(String head, Double d);
}

public class LambdaExpressions {

    static Body body = h -> h + "No parens!";

    static Body body2 = (h) -> h + "More details";

    static Description description = () -> "Short info";

    // 单行不能return
    static Multi multi = (h, n) -> h + n;

    // 多行必须return
    static Description moreLines = () -> {
        System.out.println("moreLines()");
        return "from moreLines";
    };

    public static void main(String[] args) {
        System.out.println(body.detailed("Oh!"));
        System.out.println(body2.detailed("Hi!"));
        System.out.println(description.brief());
        System.out.println(multi.twoArg("Pi!", 3.14159));
        System.out.println(moreLines.brief());
    }
}
