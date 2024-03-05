package com.sermaluc.challenge.user.domain.usecase;

import com.sermaluc.challenge.user.adapter.controller.model.Phones;
import com.sermaluc.challenge.user.adapter.controller.model.in.UserRequest;
import com.sermaluc.challenge.user.adapter.controller.model.out.AppUserDTO;
import com.sermaluc.challenge.user.adapter.controller.util.RequestValidate;
import com.sermaluc.challenge.user.config.exceptions.BusinessException;
import com.sermaluc.challenge.user.config.exceptions.enums.ErrorResponses;
import com.sermaluc.challenge.user.config.token.JwtTokenProvider;
import com.sermaluc.challenge.user.domain.model.entity.AppUser;
import com.sermaluc.challenge.user.domain.model.entity.UserPhone;
import com.sermaluc.challenge.user.domain.model.entity.enums.AppUserRole;
import com.sermaluc.challenge.user.domain.model.entity.enums.UserStatus;
import com.sermaluc.challenge.user.domain.port.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserManagementUCImpl implements UserManagementUC {
    private final UserManagementService userManagementService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private  void throwIfUserExist(final String email){
        AppUser appUser = userManagementService.findUserByEmail(email);
        if(Objects.nonNull(appUser)) {
            throw new BusinessException(ErrorResponses.EMAIL_EXIST);
        }
    }

    private List<UserPhone> buildPhones(final UserRequest userRequest) {
        return userRequest.getPhones().stream().map(p-> UserPhone.builder()
                .cityCode(p.getCityCode())
                .phoneNumber(p.getNumber())
                .countryCode(p.getCountryCode())
                .build()).collect(Collectors.toList());
    }

    @Override
    public AppUserDTO register(UserRequest userRequest) {
        RequestValidate.throwIfEmailInvalid(userRequest.getEmail());
        RequestValidate.throwIfPassRules(userRequest.getPassword());
        throwIfUserExist(userRequest.getEmail());


        AppUser appUser = new AppUser();

        appUser.setUsername(userRequest.getEmail());
        appUser.setFullName(userRequest.getName());
        appUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        String token = jwtTokenProvider.createToken(userRequest.getEmail(),
                new ArrayList<>(Arrays.asList(AppUserRole.CLIENT)));
        appUser.setUserToken(token);
        appUser.setStatus(UserStatus.ACTIVE);
        LocalDateTime now = LocalDateTime.now();
        appUser.setCreatedDate(now);
        appUser.setModifiedDate(now);
        appUser.setLastLogin(now);
        appUser.setPhoneList(buildPhones(userRequest));
        return buildUserDTO(userManagementService.save(appUser), userRequest);
    }

    @Override
    public List<AppUserDTO> listAppUser() {
        return  buildListUserDTO( userManagementService.listAppUser());
    }

    private AppUserDTO buildUserDTO(AppUser appUser, UserRequest userRequest) {
        return AppUserDTO.builder()
                .id(appUser.getUserId())
                .registered(appUser.getCreatedDate())
                .updated(appUser.getModifiedDate())
                .lastLogin(appUser.getLastLogin())
                .state(appUser.getStatus().name())
                .email(appUser.getUsername())
                .name(appUser.getFullName())
                .token(appUser.getUserToken())
                .phones(userRequest.getPhones())
                .build();
    }

    private List<AppUserDTO> buildListUserDTO(List<AppUser> listAppUser) {
        List <AppUserDTO> list = new ArrayList<>();
           for (AppUser appUser  : listAppUser) {
               list.add( AppUserDTO.builder()
                       .id(appUser.getUserId())
                       .registered(appUser.getCreatedDate())
                       .updated(appUser.getModifiedDate())
                       .lastLogin(appUser.getLastLogin())
                       .state(appUser.getStatus().name())
                       .email(appUser.getUsername())
                       .name(appUser.getFullName())
                       .token(appUser.getUserToken())
                       .build());
           }
           return list;
    }
}
