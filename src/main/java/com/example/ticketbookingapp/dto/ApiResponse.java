package com.example.ticketbookingapp.dto;

public class ApiResponse<T> {
    private boolean success;
    private String error;
    private T data;
    
    public ApiResponse(boolean success, String error, T data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public ApiResponse() {
        
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiResponse<T> constructResponse(T data, String error) {
        if (error == null) {
            this.setSuccess(true);
            this.setData(data);
        } else {
            this.setSuccess(false);
            this.setError(error);
        }

        return this;
    }
}
