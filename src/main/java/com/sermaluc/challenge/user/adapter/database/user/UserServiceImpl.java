package com.sermaluc.challenge.user.adapter.database.user;

import com.sermaluc.challenge.user.adapter.database.repository.UserRepository;
import com.sermaluc.challenge.user.domain.model.entity.AppUser;
import com.sermaluc.challenge.user.domain.port.UserManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserManagementService {
    private final UserRepository userRepository;
    @Override
    public AppUser findUserByEmail(String email) {
        return userRepository.findUserByUsername(email);
    }

    public AppUser save(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public List<AppUser> listAppUser() {
        return userRepository.findAll();
    }
}
