package br.ufpb.dcx.oppfyhub.opportunityhub.controller;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.LoginUserDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.ResponseLoginDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.service.AuthenticationIntermediary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationIntermediary authenticationIntermediary;

    @Operation(
            summary = "Login user",
            description = "Login user by user password and email"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User token",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(example = "token : token")) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized user",
                    content = @Content),
    })
    @PostMapping("auth/login")
    @ResponseStatus(code= HttpStatus.OK)
    public ResponseLoginDTO auth(@RequestBody LoginUserDTO user) {
        return authenticationIntermediary.authenticate(user);
    }



}
