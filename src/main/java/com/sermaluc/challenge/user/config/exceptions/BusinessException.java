package com.sermaluc.challenge.user.config.exceptions;

import com.sermaluc.challenge.user.config.exceptions.enums.ErrorResponses;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "business error")
@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 10l;
    private final ErrorResponses code;

    public BusinessException(ErrorResponses code) {
        super("No valid user to make the request");
        this.code = code;
    }

    public ErrorResponses getCode() {
        return code;
    }
}
