package com.sermaluc.challenge.user.config.exceptions.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum ErrorResponses {
    PASS_INVALID(100, "Invalid password, must contain one Shift, lowercase letters, and two numbers"),
    EMAIL_INVALID(100, "Invalid email format"),
    EMAIL_EXIST(100, "Email already exists");

    private final int code;
    private final String description;
}
