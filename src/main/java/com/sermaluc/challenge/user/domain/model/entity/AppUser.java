package com.sermaluc.challenge.user.domain.model.entity;

import com.sermaluc.challenge.user.domain.model.entity.enums.AppUserRole;
import com.sermaluc.challenge.user.domain.model.entity.enums.UserStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @Column(name = "id", unique = true)
    private String userId;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "user_token")
    private String userToken;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @ElementCollection(fetch = FetchType.EAGER)
    List<AppUserRole> appUserRoles;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserPhone> phoneList = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        setUserId(java.util.UUID.randomUUID().toString());
    }
}
