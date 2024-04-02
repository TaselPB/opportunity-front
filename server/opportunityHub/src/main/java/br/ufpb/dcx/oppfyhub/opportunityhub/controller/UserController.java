package br.ufpb.dcx.oppfyhub.opportunityhub.controller;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserRequestDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.UserResponseDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.entity.User;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;


    // Gets

    @Operation(
            summary = "Search user by email",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Search user by email, passing the header authorization token, if the token not for this user or this user not exists for this email return unauthorized"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
    })
    @GetMapping("/auth/users/{email}")
    @ResponseStatus(code=HttpStatus.OK)
    public UserResponseDTO getUser(@PathVariable String email,
                                   @Parameter(description = "Bearer token authorization", required = true,hidden = true , schema = @Schema(implementation = String.class)) @RequestHeader("Authorization") String header) {
       return userService.getUserByEmail(email, header);
    }



    // Posts
    @Operation(
            summary = "Create user",
            description = "Create user passing json request body, if this user email already exists return 409"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return user created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "409", description = "Conflict if this user already exists",
                    content = @Content)
    })
    @PostMapping("v1/api/users")
    @ResponseStatus(code=HttpStatus.CREATED)
    public UserResponseDTO registerUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.registerUser(userRequestDTO);
    }



    // Deletes
    @Operation(
            summary = "Delete user by email",
            security = @SecurityRequirement(name = "bearerAuth"),
            description = "Delete user by email, passing the header authorization token, if the token not for this user or this token user not is Professor return unauthorized and this user not exists for this email 404"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @DeleteMapping("/auth/users/{email}")
    @ResponseStatus(code=HttpStatus.OK)
    public UserResponseDTO removeUser(@PathVariable String email,
                                      @Parameter(description = "Bearer token authorization", required = true,hidden = true , schema = @Schema(implementation = String.class)) @RequestHeader("Authorization") String header) {
        return userService.removeUser(email, header);
    }

}
