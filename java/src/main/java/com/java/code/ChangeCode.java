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
import java.util.stream.Collectors;

public class ChangeCode {

    public static void main(String[] args) throws Exception {

        List<String> interfaceFiles = new ArrayList<>();
        List<String> implFiles = new ArrayList<>();

        File interfaceFile = new File("D:\\learnBySelf\\code\\design_implementation\\aps-common\\src\\main\\java\\com" +
                "\\changhong\\core\\facade\\api\\invest");
        addFiles(interfaceFile, interfaceFiles);

        File implFile = new File("D:\\learnBySelf\\code\\design_implementation\\aps-core-boot\\src\\main\\java\\com" +
                "\\ylink\\aps\\core\\biz\\invest");

        addFiles(implFile, implFiles);

        Map<String, String> map = implFiles.stream().filter(s -> s.contains("Impl")).collect(
                Collectors.toMap(s -> s.substring(s.lastIndexOf("\\") + 1).replace("Impl", ""), s -> s));

        for (String interfacePath : interfaceFiles) {

            String interfaceName= interfacePath.substring(interfacePath.lastIndexOf("\\") + 1);
            if (!map.containsKey(interfaceName)) {
                continue;
            }

            List<String> methodsName = changeCode(interfacePath);

            changeImplCode(map.get(interfaceName), methodsName);
        }

//        List<String> methodsName = changeCode("D:\\learnBySelf\\code\\design_implementation\\aps-common\\src\\main\\java\\com\\changhong\\core" +
//                "\\facade\\api\\institution\\InstitutionAppService.java");
//        changeImplCode("D:\\learnBySelf\\code\\design_implementation\\aps-core-boot\\src\\main\\java\\com\\ylink\\aps" +
//                "\\core\\biz\\institution\\InstitutionAppServiceImpl.java", methodsName);
    }

    /**
     * 递归添加文件名
     * @param file 文件名/文件夹
     * @param filesName 文件名列表
     */
    private static void addFiles(File file, List<String> filesName) {

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
                addFiles(f, filesName);
            } else {
                filesName.add(f.getAbsolutePath());
            }
        }
    }

    public static void changeImplCode(String filePath, List<String> methodsName) throws Exception {

        List<String> lines = getAllCode(filePath);

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
                }
                result.add("import com.changhong.boot.ChPayRequestBody;");
            }


            if (!isOnlyAddChPayRequestBody && lines.get(index).startsWith("@Service")) {
                result.add("@RestController");
                index++;
                result.add("//" + lines.get(index));
            }

            if (!isOnlyAddChPayRequestBody && lines.get(index).startsWith("@Component")) {
                index++;
                result.add("//" + lines.get(index));
            }

            // 统一去除@Override注解, 之后再补充, 防止有些实现方法没加@Override注解
            if (lines.get(index).contains("@Override")) {
                index++;
                // 补充非接口方法的@Override注解和已经添加好的@Override注解
                if (!isInterfaceMethod(lines.get(index), methodsName) || isOnlyAddChPayRequestBody) {
                    result.add("    @Override");
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

        // 写入文件
        FileWriter fileWriter = new FileWriter(filePath);
        for (String str : result) {
            fileWriter.write(str + '\n');
        }

        fileWriter.close();
    }

    public static List<String> changeCode(String filePath) throws Exception {

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
                    result.add("@FeignClient(value = \"aps-core-boot\", configuration = ChPayFeignSupportConfig.class)");
                }
                interfaceLine = index;
            }

            if (interfaceLine > 0 && index > interfaceLine && lines.get(index).contains("(") && !lines.get(index).contains("*")) {

                // 补充方法注解
                if (!isOnlyAddChPayRequestBody) {
                    result.add("    @ChPayMapping");
                }

                // 获取完整的方法代码
                String methodStr = lines.get(index);
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
     * 读取文件的全部代码
     * @param filePath 文件路径
     * @return 分行代码
     * @throws Exception 异常
     */
    private static List<String> getAllCode(String filePath) throws Exception {

        filePath = filePath.replaceAll("\\\\", "/");
        Path path = Paths.get(filePath);

        return Files.readAllLines(path);
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

        // 使用逗号定位, 并排除可能存在的返回类型的泛型逗号, 暂未处理第二个及之后的参数为多泛型的情况, 如Map<String, String>
        int commaIndex = code.indexOf(",", leftBracketsIndex);
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

            commaIndex = code.indexOf(",");
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

//    private int findParamComma(String code) {
//
//
//    }

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
            if (methodCode.contains(str)) {
                return true;
            }
        }

        return false;
    }
}