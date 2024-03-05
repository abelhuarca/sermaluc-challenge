package com.sermaluc.challenge.user.adapter.controller.util;

import com.sermaluc.challenge.user.config.exceptions.BusinessException;
import com.sermaluc.challenge.user.config.exceptions.enums.ErrorResponses;
import com.sermaluc.challenge.user.domain.port.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class RequestValidate {

    private final UserManagementService userManagementService;

    private static final String REGX_MAIL_VALIDATE = "^(.+)@(domain.cl)$";
    private static String RGX_PASS_VALIDATE = "^(?=.*\\d){2}(?=.*[a-z])(?=.*[A-Z]).{4,}$";

    public static  void throwIfEmailInvalid(final String email) {
        Pattern pattern = Pattern.compile(REGX_MAIL_VALIDATE);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new BusinessException(ErrorResponses.EMAIL_INVALID);
        }
    }

    public static void throwIfPassRules(final String pass){
        Pattern pattern = Pattern.compile(RGX_PASS_VALIDATE);
        Matcher matcher = pattern.matcher(pass);
        if(!matcher.matches()){
            throw new BusinessException(ErrorResponses.PASS_INVALID);
        }
    }
}
