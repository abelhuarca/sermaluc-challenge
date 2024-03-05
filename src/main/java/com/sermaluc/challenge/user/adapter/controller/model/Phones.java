package com.sermaluc.challenge.user.adapter.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Phones {
    private String number;
    private String cityCode;
    private String countryCode;
}
