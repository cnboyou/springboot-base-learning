package com.ehi.string;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TestString
 *
 * @Author: WangYiHai
 * @Date: 2020/5/14 11:22
 * @Description: TODO
 */
public class TestString {

    public static void main(String[] args){
        String str = "11 1";

        //-----------------------判断字符串是否为空---------------------
        if (StringUtils.isEmpty(str)) {

        }
        //如果字符串都是空格为false
        if (StringUtils.isBlank(str)) {

        }

        //--------------------------字符串固定长度-------------------------------------------
        // 字符串固定长度 8位，若不足，往左补 0
        String str1 = StringUtils.leftPad(str, 8, "0");

        //--------------------------字符串关键字替换---------------------------
        // 默认替换所有关键字
        StringUtils.replace("aba", "a", "z");   //= "zbz";
        // 替换关键字，仅替换一次
        StringUtils.replaceOnce("aba", "a", "z");   //= "zba";
        // 使用正则表达式替换
        StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "");  //= "ABC123"；

        //----------------------------字符串拼接----------------------------
        String[] array = new String[]{"a", "b", "c"};
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : array) {
            stringBuilder.append(s).append(";");
        }
        // 防止最终拼接字符串为空
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        System.out.println(stringBuilder.toString());

        //上面这段代码非常容易出错，容易抛出 StringIndexOutOfBoundsException
        //我们可以直接使用以下方法获取拼接之后字符串
        StringUtils.join(array,",");   //= "a,b,c"

        //集合拼接，所以再推荐下 Joiner
        String[] array1 = new String[]{"test", "1234", "5678"};
        List<String> list=new ArrayList<>();
        list.add("test");
        list.add("1234");
        list.add("5678");
        StringUtils.join(array1, ",");

        // 逗号分隔符，跳过 null
        Joiner joiner=Joiner.on(",").skipNulls();
        joiner.join(array);
        joiner.join(list);

        //------------------------字符串拆分-------------------------
        StringUtils.split("a..b.c", '.');  //= ["a", "b", "c"]
        StringUtils.splitByWholeSeparatorPreserveAllTokens("a..b.c", "."); //= ["a","", "b", "c"]

        // StringUtils 拆分之后得到是一个数组，我们可以使用 Guava 的
        Splitter splitter = Splitter.on(",");
        // 返回是一个 List 集合，结果：[ab, , b, c]
        splitter.splitToList("ab,,b,c");
        // 忽略空字符串，输出结果 [ab, b, c]
        splitter.omitEmptyStrings().splitToList("ab,,b,c");






    }

}