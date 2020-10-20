package com.java.on.java8.stream.create;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomWords implements Supplier<String> {

    List<String> words = new ArrayList<>();

    Random rand = new Random(47);

    public RandomWords(String fname) throws IOException {
        String systemContent = System.getProperty("user.dir");
        List<String> lines = Files.readAllLines(Paths.get(systemContent + "/java/src/" + fname));

        for (String line : lines.subList(1, lines.size())) {
            // 使用空格或标点符号分割字符串
            for (String word : line.split("[ .?,]+")) {
                words.add(word.toLowerCase());
            }
        }
    }

    @Override
    public String get() {
        return words.get(rand.nextInt(words.size()));
    }

    @Override
    public String toString() {
        return String.join(" ", words);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(
                //  Stream.generate() 可以把任意 Supplier<T> 用于生成 T 类型的流。
                Stream.generate(new RandomWords("Cheese.dat"))
                        .limit(10)
                        // 返回类型为R, 泛型为<R, A>, 参数类型为Collector<? super T, A, R>
                        .collect(
                                // 返回类型为String, 流中的全部元素被joining参数隔开
                                // 参数类型为Collector<CharSequence, ?, String>
                                Collectors.joining(" "))
        );
//        System.out.println(Paths.get(".").toAbsolutePath());
    }
}
