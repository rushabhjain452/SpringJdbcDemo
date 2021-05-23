package com.tutorialspoint.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    @JsonIgnore
    private int code;
    private String message;
    private T data;

    public Result() {
        super();
    }

    public Result(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Result(int code, T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public Result(int code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EmployeeSystemResponse [message=" + message + ", data=" + data + "]";
    }

}
