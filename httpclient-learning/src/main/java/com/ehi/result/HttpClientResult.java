package com.ehi.result;

import java.io.Serializable;

/**
 * ClassName: HttpClientResult
 *
 * @Author: WangYiHai
 * @Date: 2020/5/6 15:15
 * @Description: 封装httpClient响应结果
 */
public class HttpClientResult implements Serializable {

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public HttpClientResult(int code) {
        this.code = code;
    }
}
