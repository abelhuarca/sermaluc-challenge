package com.sermaluc.challenge.user.adapter.controller;

import com.sermaluc.challenge.user.adapter.controller.model.in.UserRequest;
import com.sermaluc.challenge.user.adapter.controller.model.out.AppUserDTO;
import com.sermaluc.challenge.user.domain.usecase.UserManagementUC;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.client.api.path}" + "/users")
@Slf4j
@Tag(name = "users", description = "Create user service")
@RequiredArgsConstructor
public class AppUserController {

    private final UserManagementUC userManagementUC;

    @Operation(summary = "Create user", description =
            "Api to create EY application User",
            tags = {"users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful user creation"),
            @ApiResponse(responseCode = "400", description = "User creation failed",
                    content = @Content(schema = @Schema(implementation = Object.class)))
    })

    @PostMapping(value = "", produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public AppUserDTO registerAppUser(@RequestBody UserRequest userRequest) {
        log.info("Starting registration : {}", userRequest.getEmail());
        return userManagementUC.register(userRequest);
    }

    @Operation(  summary = "Demo list User to test Token", description = "Api to test token",
            tags = {"listAppUser"})
    @GetMapping(value = "list", produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public List<AppUserDTO> listAppUser() {
        log.info("Starting get listUsers : {}");
        return userManagementUC.listAppUser();
    }
}
