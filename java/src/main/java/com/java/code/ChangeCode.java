package com.java.code;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChangeCode {

    public static void main(String[] args) throws Exception {


        changeCode("D:\\learnBySelf\\code\\design_implementation\\aps-common\\src\\main\\java\\com\\ylink\\aps\\app" +
                        "\\device",

                "D:\\learnBySelf\\code\\design_implementation\\hibi-common\\src\\main\\java\\com\\ylink\\hibi\\base",

                false);


//        List<String> methodsName = changeInterfaceCode("D:\\learnBySelf\\code\\design_implementation\\hibi-common" +
//                "\\src\\main\\java\\com\\ylink\\hibi\\app\\settle\\query");
//        changeImplCode("D:\\learnBySelf\\code\\design_implementation\\hibi-settle-boot\\src\\main\\java\\com" +
//                "\\changhong\\hibi\\settle\\app\\query\\ProfitFeeAppServiceImpl.java", methodsName);
    }

    /**
     * 代码补充注解
     * @param interfaceRootPath 接口目录
     * @param implRootPath 实现类目录
     * @param isChanged 代码是否已经修改过, 只需要给@FeignClient添加primary = false和给实现类补充@Primary注解
     * @throws Exception 异常
     */
    private static void changeCode(String interfaceRootPath, String implRootPath, boolean isChanged) throws Exception {

        List<String> interfaceFiles = new ArrayList<>();
        List<String> implFiles = new ArrayList<>();

        addFiles(interfaceRootPath, interfaceFiles);
        System.out.println("");

        addFiles(implRootPath, implFiles);

        Map<String, String> map = implFiles.stream().filter(s -> s.contains("Impl")).collect(
                Collectors.toMap(s -> s.substring(s.lastIndexOf("\\") + 1)
                        .replace("Impl", ""), s -> s));

        for (String interfacePath : interfaceFiles) {

            String interfaceName = interfacePath.substring(interfacePath.lastIndexOf("\\") + 1);
            if (!map.containsKey(interfaceName)) {
                continue;
            }

            if (isChanged) {

                changeInterfaceCodeForPrimary(interfacePath);
                changeImplCode(map.get(interfaceName));
                continue;
            }

            List<String> methodsName = changeInterfaceCode(interfacePath);

            changeImplCode(map.get(interfaceName), methodsName);
        }
    }

    /**
     * 读写文件操作
     * @param filePath 文件禄路径
     * @param function 处理操作
     */
    private static void readAndWriteFile(String filePath, Function<List<String>, List<String>> function) {

        List<String> lines = getAllCode(filePath);
        List<String> result = function.apply(lines);

        try {

            // 写入文件
            FileWriter fileWriter = new FileWriter(filePath);
            for (String str : result) {
                fileWriter.write(str + '\n');
            }

            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 补充primary = false
     * @param filePath 文件路径
     */
    private static void changeInterfaceCodeForPrimary(String filePath) {

        readAndWriteFile(filePath, lines -> {

            List<String> result = new LinkedList<>();

            for (int index = 0; index < lines.size(); index++) {

                if (lines.get(index).contains("@FeignClient")) {
                    result.add("@FeignClient(value = \"risk-market\", configuration = ChPayFeignSupportConfig.class, " +
                            "primary = false)");
                    index++;
                }

                result.add(lines.get(index));
            }

            return result;
        });
    }

    /**
     * 给实现类补充@Primary注解
     * @param filePath 实现类地址
     */
    private static void changeImplCode(String filePath) {

        readAndWriteFile(filePath, lines -> {

            List<String> result = new LinkedList<>();

            for (int index = 0; index < lines.size(); index++) {

                String line = lines.get(index);
                if (line.contains("import com.changhong.boot.ChPayRequestBody;")) {
                    result.add("import org.springframework.context.annotation.Primary;");
                }

                if (line.contains("@RestController")) {
                    result.add(line);
                    line = lines.get(++index);
                    result.add("@Primary");
                }

                if (line.contains("@Service") || line.contains("@Component")) {
                    continue;
                }

                result.add(line);
            }

            return result;
        });
    }

    /**
     * 递归添加文件名
     * @param filePath 文件根路径
     * @param filesName 文件名列表
     */
    private static void addFiles(String filePath, List<String> filesName) {

        File file = new File(filePath);

        if (!file.isDirectory()) {
            filesName.add(file.getAbsolutePath());
            return;
        }

        File[] files = file.listFiles();

        if (files == null || files.length == 0) {
            return;
        }

        for (File f : files) {
            if (f.isDirectory()) {
                addFiles(filePath, filesName);
            } else {
                filesName.add(f.getAbsolutePath());
            }
        }
    }

    public static void changeImplCode(String filePath, List<String> methodsName) {

        readAndWriteFile(filePath, lines -> {

            List<String> result = new LinkedList<>();

            // 是否只补充@ChPayRequestBody注解
            boolean isOnlyAddChPayRequestBody = false;

            for (int index = 0; index < lines.size(); index++) {

                if (!isOnlyAddChPayRequestBody && lines.get(index).contains("ChPayMapping")) {
                    isOnlyAddChPayRequestBody = true;
                }

                // 补充导入的包
                if (lines.get(index).contains("package")) {
                    result.add(lines.get(index));
                    result.add("");
                    index++;
                    index++;
                    if (!isOnlyAddChPayRequestBody) {
                        result.add("import com.changhong.boot.ChPayMapping;");
                        result.add("import org.springframework.web.bind.annotation.RestController;");
                        result.add("import org.springframework.context.annotation.Primary;");
                    }
                    result.add("import com.changhong.boot.ChPayRequestBody;");
                }


                if (!isOnlyAddChPayRequestBody && lines.get(index).startsWith("@Service")) {
                    result.add("@RestController");
                    result.add("@Primary");
                    index++;
                }

                if (!isOnlyAddChPayRequestBody && lines.get(index).startsWith("@Component")) {
                    index++;
                }

                // 统一去除@Override注解, 之后再补充, 防止有些实现方法没加@Override注解
                if (lines.get(index).contains("@Override")) {
                    index++;
                    // 补充非接口方法的@Override注解和已经添加好的@Override注解
                    boolean needAddOverride = needAddOverride(lines, index, methodsName);
                    if (needAddOverride || isOnlyAddChPayRequestBody) {
                        result.add("\t@Override");
                    }
                }

                if (isInterfaceMethod(lines.get(index), methodsName)) {

                    if (!isOnlyAddChPayRequestBody) {
                        // 补充方法注解
                        result.add("    @ChPayMapping");
                        result.add("    @Override");
                    }

                    // 获取完整的方法代码
                    String methodStr = lines.get(index);

                    if (!methodStr.contains(")")) {
                        do {
                            index++;
                            methodStr = methodStr.concat(lines.get(index));
                        } while (!methodStr.contains("{"));
                    }

                    // 给方法参数添加注解
                    dealMethod(methodStr, result);

                    index++;
                    if (index >= lines.size()) {
                        break;
                    }
                }

                result.add(lines.get(index));
            }

            return result;
        });
    }

    public static List<String> changeInterfaceCode(String filePath) throws Exception {

        List<String> methodsName = new ArrayList<>();

        List<String> lines = getAllCode(filePath);

        List<String> result = new LinkedList<>();

        // 是否只补充@ChPayRequestBody注解
        boolean isOnlyAddChPayRequestBody = false;

        int interfaceLine = 0;
        for (int index = 0; index < lines.size(); index++) {

            // 补充导入的包
            if (lines.get(index).contains("package")) {
                result.add(lines.get(index));
                result.add("");
                index++;
                index++;

                if (!isOnlyAddChPayRequestBody) {
                    result.add("import com.changhong.boot.ChPayMapping;");
                }
                result.add("import com.changhong.boot.ChPayRequestBody;");
                if (!isOnlyAddChPayRequestBody) {
                    result.add("import com.changhong.boot.consumer.ChPayFeignSupportConfig;");
                    result.add("import org.springframework.cloud.openfeign.FeignClient;");
                }
            }

            if (!isOnlyAddChPayRequestBody && lines.get(index).contains("ChPayMapping")) {
                isOnlyAddChPayRequestBody = true;
            }

            // 补充接口注解
            if (lines.get(index).contains("public interface")) {
                if (!isOnlyAddChPayRequestBody) {
                    result.add("@FeignClient(value = \"risk-market\", configuration = ChPayFeignSupportConfig" +
                            ".class, primary = false)");
                }
                interfaceLine = index;
            }

            if (interfaceLine > 0 && index > interfaceLine &&
                    lines.get(index).contains("(") &&
                    // 排除注释的干扰
                    !(lines.get(index).contains("*") || lines.get(index).contains("//"))) {

                // 补充方法注解
                if (!isOnlyAddChPayRequestBody) {
                    result.add("    @ChPayMapping");
                }

                // 获取完整的方法代码
                String methodStr = lines.get(index);
                // 去除接口方法多余的public和空格
                methodStr = methodStr.replace("public ", "");
                // 记录方法名
                methodsName.add(getMethodName(methodStr));

                if (!methodStr.contains(";")) {
                    do {
                        index++;
                        methodStr = methodStr.concat(lines.get(index));
                    } while (!methodStr.contains(";"));
                }

                // 给方法参数添加注解
                dealMethod(methodStr, result);

                index++;
                if (index >= lines.size()) {
                    break;
                }
            }

            result.add(lines.get(index));
        }

        // 写入文件
        FileWriter fileWriter = new FileWriter(filePath);
        for (String str : result) {
            fileWriter.write(str + '\n');
        }

        fileWriter.close();

        return methodsName;
    }

    /**
     * 判断是否需要单独添加@Override注解
     * @param lines 代码
     * @param overrideIndex 存在的@Override注解索引
     * @param methodsName 接口方法名
     * @return 是否应该添加@Override注解
     */
    private static boolean needAddOverride(List<String> lines, int overrideIndex, List<String> methodsName) {

        while (true) {

            // 排除其他注解和注释
            if (lines.get(overrideIndex).contains("@") || lines.get(overrideIndex).contains("//") ||
                    lines.get(overrideIndex).contains("*")) {
                overrideIndex++;

            } else {
                return !isInterfaceMethod(lines.get(overrideIndex),methodsName);
            }
        }
    }

    /**
     * 读取文件的全部代码
     * @param filePath 文件路径
     * @return 分行代码
     * @throws Exception 异常
     */
    private static List<String> getAllCode(String filePath) {

        filePath = filePath.replaceAll("\\\\", "/");
        Path path = Paths.get(filePath);

        try {

            return Files.readAllLines(path);

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("文件写入失败");

    }

    /**
     * 获取接口的方法名
     * @param methodStr 含有方法名的代码
     * @return 方法名
     */
    public static String getMethodName(String methodStr) {

        int bracketsIndex = methodStr.indexOf("(");

        int startIndex = methodStr.substring(0, bracketsIndex).lastIndexOf(" ");
        return methodStr.substring(startIndex + 1, bracketsIndex);
    }

    /**
     * 给方法参数添加@ChPayRequestBody注解
     * @param code 方法代码
     * @param results 结果
     */
    public static void dealMethod(String code, List<String> results) {

        // 处理空参数
        if (code.contains("()")) {
            results.add(code);
            return;
        }

        // 第一个参数的注解借助方法的左括号定位进行添加
        int leftBracketsIndex = code.indexOf("(");

        code = code.substring(0, leftBracketsIndex+1).concat("@ChPayRequestBody ")
                .concat(code.substring(leftBracketsIndex+1));

        // 使用逗号定位, 并排除可能存在的返回类型的泛型逗号
        int commaIndex = findParamComma(code);
        if (commaIndex < 0) {
            results.add(code);
            return;
        }

        // 之后的通过逗号定位进行添加
        String result;

        result = code.substring(0, commaIndex).concat(", ");
        code = code.substring(commaIndex+1);
        // 去除开头的空格, 包括制表符等
        code = code.replaceFirst( "^\\s+", "");

        while (true) {

            commaIndex = findParamComma(code);
            if (commaIndex < 0) {
                result = result.concat("@ChPayRequestBody ").concat(code);
                break;
            }
            result = result.concat("@ChPayRequestBody ").concat(code.substring(0, commaIndex)).concat("," +
                    " ");
            code = code.substring(commaIndex+1);
            // 只去除开头的空格
            code = code.replaceFirst( "^\\s+", "");
        }

        results.add(result);
    }

    /**
     * 找到参数后面的逗号,而不是泛型中的逗号
     * @param code 代码
     * @return 参数后面逗号的索引
     */
    private static int findParamComma(String code) {

        int firstCommaIndex = code.indexOf(",");

        if (firstCommaIndex < 0) {
            return firstCommaIndex;
        }

        int nextCommaIndex;

        while (true) {

            nextCommaIndex = code.indexOf(",", firstCommaIndex+1);
            if (nextCommaIndex == -1) {
                nextCommaIndex = code.indexOf(")");
            }

            // 逗号的索引
            if (nextCommaIndex < firstCommaIndex) {
                return -1;
            }

            String containsCommaCode = code.substring(firstCommaIndex);

            // 根据泛型中的尖括号数量判断逗号是泛型的逗号还是参数后面的逗号
            if (containsCommaCode.split("<").length == containsCommaCode.split(">").length) {

                // 2个索引相同, 此时泛型参数为最后一个参数, 直接返回-1
                if (firstCommaIndex == nextCommaIndex) {
                    return -1;
                }
                // 否则返回泛型参数后逗号的索引
                return firstCommaIndex;
            }

            firstCommaIndex = nextCommaIndex;
        }
    }

    /**
     * 判断实现类方法是否是接口的方法
     * @param methodCode 实现类包含方法名的代码行
     * @param methodsName 接口方法
     * @return 是否是接口方法
     */
    private static boolean isInterfaceMethod(String methodCode, List<String> methodsName) {

        if (!methodCode.contains("public")) {
            return false;
        }

        for (String str : methodsName) {
            if (methodCode.contains(str+"(")) {
                return true;
            }
        }

        return false;
    }
}
