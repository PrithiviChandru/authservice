//package com.micro.authservice.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.Data;
//
//import java.util.List;
//
//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class BaseResponse<T> {
//
//    private boolean apiStatus;
//    private String message;
//    private String trace;
//
//    private T value;
//    private List<T> values;
//
//    public static <T> BaseResponse<T> success(T value, String message) {
//        BaseResponse<T> response = new BaseResponse<>();
//        response.setApiStatus(true);
//        response.setMessage(message);
//        response.setValue(value);
//        return response;
//    }
//
//    public static <T> BaseResponse<T> successList(List<T> values, String message) {
//        BaseResponse<T> response = new BaseResponse<>();
//        response.setApiStatus(true);
//        response.setMessage(message);
//        response.setValues(values);
//        return response;
//    }
//
//    public static <T> BaseResponse<T> failure(String trace, String message) {
//        BaseResponse<T> response = new BaseResponse<>();
//        response.setApiStatus(false);
//        response.setMessage(message);
//        response.setTrace(trace);
//        return response;
//    }
//}
