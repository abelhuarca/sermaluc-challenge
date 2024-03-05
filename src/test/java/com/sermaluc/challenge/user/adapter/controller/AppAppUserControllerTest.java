package com.sermaluc.challenge.user.adapter.controller;

import com.sermaluc.challenge.user.adapter.controller.model.Phones;
import com.sermaluc.challenge.user.adapter.controller.model.in.UserRequest;
import com.sermaluc.challenge.user.adapter.controller.model.out.AppUserDTO;
import com.sermaluc.challenge.user.domain.usecase.UserManagementUC;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Rest Controller User")
class AppAppUserControllerTest {

    @Mock
    private UserManagementUC userManagementUC;
    @InjectMocks
    private AppUserController userController;

    @TestFactory
    @DisplayName("When Execute Register User, Successful Process")
    List<DynamicTest> whenExecuteUserSuccessfullProcess()  {
        UserRequest userRequest = new UserRequest();
        String email = "mailexample@gmail.com";
        userRequest.setEmail(email);
        userRequest.setName("Pepito Medina");
        userRequest.setPassword("12548DemoPass");
                Phones phones = new Phones();
                phones.setCityCode("cityCodeExample");
                phones.setCountryCode("countryCodeExample");
                phones.setNumber("987654321");
        userRequest.setPhones(Arrays.asList(
                phones
        ));

        when(userManagementUC.register(any())).thenReturn(AppUserDTO.builder()
                .email(email)
                .build());

        AppUserDTO response = Assertions.assertDoesNotThrow(
                () -> userController.registerAppUser(userRequest));
        return Arrays.asList(
                DynamicTest.dynamicTest("Register user",
                        () -> Assertions.assertEquals(email, response.getEmail())),
                DynamicTest.dynamicTest("Use case process is performed one time",
                        () -> Mockito.verify(userManagementUC, times(1)).register(any()))
        );

    }

}