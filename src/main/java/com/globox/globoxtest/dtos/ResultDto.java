package com.globox.globoxtest.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
@Builder
public class ResultDto<T> {
    Boolean isSuccess;
    Integer statusCode;
    String message;
    T result;
}
