package com.student.score.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * <p>
 * 统一返回结果封装类
 * </p>
 *
 * @author seekpan
 */
@NoArgsConstructor
@Data
public class RestResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public RestResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RestResult<T> success(T data) {
        RestResult<T> result = new RestResult<>();
        result.setCode("000000");
        result.setMsg("调用成功");
        result.setData(data);
        return result;
    }

    /**
     * 失败响应
     */
    public static <T> RestResult<T> error(String code, String msg) {
        return new RestResult<>(code, msg, null);
    }

    public static <T> RestResult<T> failure(String code, String msg) {
        return new RestResult<>(code, msg, null);
    }

    public static <T> RestResult<T> failure(String code, String msg, T data) {
        return new RestResult<>(code, msg, data);
    }

    public static <T> RestResult<T> error(String msg) {
        RestResult<T> result = new RestResult<>();
        result.setCode("000001");
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
