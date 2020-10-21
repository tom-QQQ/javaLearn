package com.java.on.java8.stream.create;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;

/**
 * 流的建造者模式
 */
public class FileToWordsBuilder {


    Stream.Builder<String> builder = Stream.builder();

    public FileToWordsBuilder(String fname) throws IOException {

        String systemContent = System.getProperty("user.dir");
        Files.lines(Paths.get(systemContent + "/java/src/" + fname))
                .skip(1)
                .forEach(line -> {
                    for (String w : line.split("[ .?,]+")) {
                        // 向builder中添加单词
                        builder.add(w);
                    }
                });
    }

    /**
     * 不调用该方法, 就可以一直向builder中添加单词
     * @return 流
     */
    Stream<String> stream() {
        return builder.build();
    }

    public static void main(String[] args) throws Exception {

        new FileToWordsBuilder("Cheese.dat")
                .stream()
                .limit(7)
                .map(w -> w.concat(" "))
                .forEach(System.out::print);
    }
}
