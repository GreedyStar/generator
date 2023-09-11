package com.dcode.test;

public class Response<T> {

    /**
     * 返回状态编码，0表示操作成功
     */
    private int code = 0;

    private T data;

    private String message;

    public Response() {

    }

    public Response(T data) {
        this.data = data;
    }

    public Response(String message) {
        this.message = message;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Response ok() {
        return new Response<>();
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(data);
    }

    public static Response<?> failed(String message) {
        return new Response<>(-1,message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
