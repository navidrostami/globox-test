package com.globox.globoxtest.util;

import com.globox.globoxtest.dtos.ResultDto;
import org.springframework.http.HttpStatus;


public class ResultUtil {
    public static <T> ResultDto<T> success(T model) {
        return new ResultDto<T>(
                true,
                HttpStatus.OK.value(),
                "success",
                model
        );
    }

    public static <T> ResultDto<T> create(T model) {
        return new ResultDto<T>(
                true,
                HttpStatus.CREATED.value(),
                "created",
                model
        );
    }

    public static <T> ResultDto<T> accepted() {
        return new ResultDto<T>(
                true,
                HttpStatus.ACCEPTED.value(),
                "Accepted",
                null
        );
    }

    public static <T> ResultDto<T> badRequest() {
        return new ResultDto<T>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                "bad Request",
                null
        );
    }

    public static <T> ResultDto<T> notFound() {
        return new ResultDto<T>(
                false,
                HttpStatus.NOT_FOUND .value(),
                "not found",
                null
        );
    }

    public static <T> ResultDto<T> error(String message) {
        return new ResultDto<>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                message,
                null
        );
    }

    public static <T> ResultDto<T> noContent() {
        return new ResultDto<T>(
                false,
                HttpStatus.NO_CONTENT.value(),
                "No Content",
                null
        );
    }
}