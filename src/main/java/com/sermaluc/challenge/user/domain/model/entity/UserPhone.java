package com.sermaluc.challenge.user.domain.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;

@Data
@Entity
@Table(name = "phone")
@Builder
public class UserPhone {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;
    private String phoneNumber;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "contry_code")
    private String countryCode;
}
