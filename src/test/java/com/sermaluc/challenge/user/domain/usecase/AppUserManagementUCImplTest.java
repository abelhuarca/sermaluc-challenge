package com.sermaluc.challenge.user.domain.usecase;

import com.sermaluc.challenge.user.adapter.controller.model.Phones;
import com.sermaluc.challenge.user.adapter.controller.model.in.UserRequest;
import com.sermaluc.challenge.user.adapter.controller.model.out.AppUserDTO;
import com.sermaluc.challenge.user.config.token.JwtTokenProvider;
import com.sermaluc.challenge.user.domain.model.entity.AppUser;
import com.sermaluc.challenge.user.domain.model.entity.enums.UserStatus;
import com.sermaluc.challenge.user.domain.port.UserManagementService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("UC insert user")
class AppUserManagementUCImplTest {
    private UserManagementUCImpl userRegisterBankUC;
    @Mock
    private UserManagementService userManagementService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        userRegisterBankUC = new UserManagementUCImpl(userManagementService,
                passwordEncoder, jwtTokenProvider);
    }

    @Test
    @DisplayName("When Register user, then Return Data.")
    void whenUserRegister() {
        String email = "mailexample@domain.cl";
        LocalDateTime testDate = LocalDateTime.now();


        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);
        userRequest.setName("Pepito Medina");
        userRequest.setPassword("12548DemoPass");

        AppUser appUserEntity = new AppUser();
        appUserEntity.setUserId("demoUuid2157845");
        appUserEntity.setUsername(email);
        appUserEntity.setStatus(UserStatus.ACTIVE);
        appUserEntity.setUserToken("yJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmVsaHJAZG9tYWluLmNsIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiQ0xJRU5UIn1dLCJpYXQiOjE2NDE1NjgxMDUsImV4cCI6MTY0MTU2ODQwNX0.4ZKtoN81sNjaS8JCUSe9CpkPKKpHHEGShJ2UjQlb4Mo");
        appUserEntity.setCreatedDate(testDate);
        appUserEntity.setModifiedDate(testDate);
        appUserEntity.setLastLogin(testDate);

        Phones phones = new Phones();
                phones.setCityCode("cityCodeExample");
                phones.setCountryCode("countryCodeExample");
                phones.setNumber("987654321");
        userRequest.setPhones(Arrays.asList(
                phones
        ));
        when(userManagementService.save(any())).thenReturn(appUserEntity);
        AppUserDTO response = userRegisterBankUC.register(userRequest);
        Assertions.assertEquals(appUserEntity.getUsername(), response.getEmail());
    }


}