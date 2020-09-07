package ru.shariktlt.hackaton2020.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
public class ApiResponse<T extends Serializable> {

    @Getter
    @Setter
    private boolean success;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private String pushUrl;


    @Getter
    @Setter
    private T payload;

    public static ApiResponse<String> pushUrl(String url) {
        return ApiResponse.<String>builder()
                .success(true)
                .pushUrl(url)
                .build();
    }

    public static ApiResponse<String> message(String message) {
        return ApiResponse.<String>builder()
                .success(true)
                .message(message)
                .build();
    }

    public static ApiResponse<String> error(String message) {
        return ApiResponse.<String>builder()
                .success(false)
                .message(message)
                .build();
    }

    public static <T extends Serializable> ApiResponse<T> success(T payload) {
        return ApiResponse.<T>builder()
                .success(true)
                .payload(payload)
                .build();
    }

    public static <T extends Serializable> ApiResponse<T> fail(T payload) {
        return ApiResponse.<T>builder()
                .success(false)
                .payload(payload)
                .build();
    }
}
