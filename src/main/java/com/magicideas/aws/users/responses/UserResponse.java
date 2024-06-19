package com.magicideas.aws.users.responses;

public class UserResponse<T>  {
    private String message;
    private int code;

    private T data;

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

