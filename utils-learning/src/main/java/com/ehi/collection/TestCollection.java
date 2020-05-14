package com.ehi.collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: TestCollection
 *
 * @Author: WangYiHai
 * @Date: 2020/5/14 11:44
 * @Description: TODO
 */
public class TestCollection {
    public static void main(String[] args){

        List list = new ArrayList();
        // List/Set 集合判空
        if(CollectionUtils.isEmpty(list)){

        }

        Map map = new HashMap();
        // Map 等集合进行判空
        if (MapUtils.isEmpty(map)) {

        }


        String[] array = new String[]{"a", "b", "c"};
        // 数组判空
        if (ArrayUtils.isEmpty(array)) {

        }

        //一些列的对于集合增强方法，比如快速将数组加入到现有集合中
        List<String> listA = new ArrayList<>();
        listA.add("1");
        listA.add("2");
        listA.add("3");
        String[] arrays = new String[]{"a", "b", "c"};
        CollectionUtils.addAll(listA, arrays);



    }
}