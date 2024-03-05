package com.sermaluc.challenge.user.adapter.database.user;

import com.sermaluc.challenge.user.adapter.database.repository.UserRepository;
import com.sermaluc.challenge.user.domain.model.entity.AppUser;
import com.sermaluc.challenge.user.domain.model.entity.UserPhone;
import com.sermaluc.challenge.user.domain.model.entity.enums.UserStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test service class")
class AppUserManagementServiceImplTest {
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("When Find by username, then Return Data.")
    void whenFindByUsernameReturnData() {
        String email = "mailexample@gmail.com";
        LocalDateTime now = LocalDateTime.now();
        AppUser appUser = new AppUser();
        appUser.setUserId("demoUuid2157845");
        appUser.setUsername(email);
        appUser.setStatus(UserStatus.ACTIVE);
        appUser.setUserToken("yJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmVsaHJAZG9tYWluLmNsIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiQ0xJRU5UIn1dLCJpYXQiOjE2NDE1NjgxMDUsImV4cCI6MTY0MTU2ODQwNX0.4ZKtoN81sNjaS8JCUSe9CpkPKKpHHEGShJ2UjQlb4Mo");
        appUser.setCreatedDate(now);
        appUser.setModifiedDate(now);
        appUser.setLastLogin(now);

        appUser.setPhoneList(Arrays.asList(
                UserPhone.builder()
                        .countryCode("051")
                        .phoneNumber("1234566")
                        .cityCode("123")
                        .build()
        ));
        when(userRepository.findUserByUsername(anyString())).thenReturn(appUser);
        AppUser appUserResponse = userService.findUserByEmail(email);

        Assertions.assertEquals(email, appUserResponse.getUsername());
    }

}