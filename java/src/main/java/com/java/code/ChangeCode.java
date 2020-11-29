package com.java.code;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChangeCode {

    public static void main(String[] args) throws Exception {


        String interfacePath = "D:\\learnBySelf\\code\\design_implementation\\aps-common\\src\\main\\java\\com" +
                "\\changhong\\core\\facade\\api\\financing\\FinancingDetailAppService.java";

        String implPath = "D:\\learnBySelf\\code\\design_implementation\\aps-core-boot\\src\\main\\java\\com\\ylink" +
                "\\aps\\core\\biz\\financing\\FinancingDetailAppServiceImpl.java";
        List<String> methodsName = changeCode(interfacePath);

        changeImplCode(implPath, methodsName);

    }

    public static void changeImplCode(String filePath, List<String> methodsName) throws Exception {

        List<String> lines = getAllCode(filePath);

        List<String> result = new LinkedList<>();

        // 是否只补充@ChPayRequestBody注解
        boolean isOnlyAddChPayRequestBody = false;

        for (int index = 0; index < lines.size(); index++) {

            if (!isOnlyAddChPayRequestBody && lines.contains("ChPayMapping")) {
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
    }

    public static List<String> changeCode(String filePath) throws Exception {

        List<String> methodsName = new ArrayList<>();

        List<String> lines = getAllCode(filePath);

        List<String> result = new LinkedList<>();

        // 是否只补充@ChPayRequestBody注解
        boolean isOnlyAddChPayRequestBody = false;

        int interfaceLine = 0;
        for (int index = 0; index < lines.size(); index++) {

            if (!isOnlyAddChPayRequestBody && lines.contains("ChPayMapping")) {
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
                }
                result.add("import com.changhong.boot.ChPayRequestBody;");
                if (!isOnlyAddChPayRequestBody) {
                    result.add("import com.changhong.boot.consumer.ChPayFeignSupportConfig;");
                    result.add("import org.springframework.cloud.openfeign.FeignClient;");
                }

            }

            // 补充接口注解
            if (!isOnlyAddChPayRequestBody && lines.get(index).contains("public interface")) {
                result.add("@FeignClient(value = \"aps-core-boot\", configuration = ChPayFeignSupportConfig.class)");
                interfaceLine = index;
            }

            if (index > interfaceLine && lines.get(index).contains("(")) {

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

        int commaIndex = code.indexOf(",");
        if (commaIndex < 0) {
            results.add(code);
            return;
        }

        // 之后的通过逗号定位进行添加
        String result;

        result = code.substring(0, commaIndex).concat(", ");
        code = code.substring(commaIndex+1);
        // 只去除开头的空格
        if (code.indexOf("^\\s+") == 0) {
            code = code.replaceFirst( "^\\s+", "");
        }
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
            if (code.indexOf("^\\s+") == 0) {
                code = code.replaceFirst( "^\\s+", "");
            }

        }

        results.add(result);
    }

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