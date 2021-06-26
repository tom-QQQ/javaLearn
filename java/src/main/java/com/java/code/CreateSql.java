package com.java.code;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ning
 * @date Create in 2019/5/8
 */
public class CreateSql {

    /**
     * 下划线匹配
     */

    private static Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");

    /**
     * 空字符串和换行制表符匹配
     */
    private static Pattern p = Pattern.compile("\\s*|\t|\r|\n");

    /**
     * 一般查询和时间数量查询
     * @param tableName 表名
     * @param params 字段名
     */
    public static void  createSql(String tableName, String... params) {
        System.out.println("where 1=1");

        for (String str : params) {

            System.out.println("<if tests=\"" + str + " != null\">");

            if (str.contains("startTime") || str.contains("min")) {
                System.out.println("    and " + tableName + "." + humpToUnderline(str) + " &gt;= #{" + str + "}");

            } else if (str.contains("endTime") || str.contains("max")) {
                System.out.println("    and " + tableName + "." + humpToUnderline(str) + " &lt;= #{" + str + "}");

            } else  {
                System.out.println("    and " + tableName + "." + humpToUnderline(str) + " = #{" + str + "}");
            }

            System.out.println("</if>");
        }
    }

    /**
     * 模糊查询
     * @param tableName 表明
     * @param params 字段
     */
    public static void createSqlWithLike(String tableName, String... params) {

        for (String str : params) {

            System.out.println("<if tests=\"" + str + " != null\">");

            System.out.println("    and " + tableName + "." + humpToUnderline(str) + " like CONCAT('%', #{" + str + "}, '%')");

            System.out.println("</if>");
        }
    }

    /**
     * 小驼峰转下划线
     * @param para 小驼峰
     * @return 下划线
     */
    public static String humpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        //定位
        int temp=0;
        for(int i=0;i<para.length();i++){
            if(Character.isUpperCase(para.charAt(i))){
                sb.insert(i+temp, "_");
                temp+=1;
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 下划线转驼峰法(默认小驼峰)
     *
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰(驼峰，第一个字符是大写还是小写)
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean ... smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Matcher matcher = pattern.matcher(line);
        //匹配正则表达式
        while (matcher.find()) {
            String word = matcher.group();
            //当是true 或则是空的情况
            if((smallCamel.length ==0 || smallCamel[0] ) && matcher.start()==0){
                sb.append(Character.toLowerCase(word.charAt(0)));
            }else{
                sb.append(Character.toUpperCase(word.charAt(0)));
            }

            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * select字段转resultMap
     * @param selectStatement select字段直接复制即可, 会去除换行符号和空格
     */
    private static void selectToMap(String selectStatement) {

        selectStatement = replaceBlank(selectStatement);
        String[] items = selectStatement.split(",");

        for (String str : items) {

            String jdbyType = "VARCHAR";

            String[] strings = str.split("\\.");
            String column = strings[1];
            String property = underline2Camel(strings[1]);

//            if (str.contains("as")) {
//                strings = str.split("as");
//                column = strings[1];
//                property = strings[1];
//            }


            if (property.contains("Time")) {
                jdbyType = "TIMESTAMP";
            }
//            else if (column.contains("id")) {
//                jdbyType = "BIGINT";
//            }
            else if (property.contains("Category") || property.contains("Type")) {
                jdbyType = "BIGINT";
            }

            System.out.println("<result column=\"" + column + "\" jdbcType=\"" + jdbyType + "\" property=\"" + property + "\"/>");
        }
    }

    private static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    public static void main(String[] args) {
        selectToMap(" task.id, task.task_id, task.principal_id, task.executive_id, task.execute_time, task.abnormal_deal_content, task.remark,\n" +
                "        task.category,task.start_date,\n" +
                "        task.end_date, task.is_completed,\n" +
                "        plan.plan_id,\n" +
                "        sys_dict.categoryName,\n" +
                "        fixture_info.fixtureSerialId, fixture_info.fixtureName,\n" +
                "        sub_plan.choice_date");
    }
}
