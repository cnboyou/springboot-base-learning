package com.ehi;

import feign.Param;
import feign.RequestLine;

/**
 * ClassName: Demo1Client
 *
 * @Author: WangYiHai
 * @Date: 2020/5/12 11:18
 * @Description: TODO
 */
public interface Demo1Client {
    @RequestLine("GET /feign/demo1?name={name}")
    String getDemo1(@Param("name") String name);

}