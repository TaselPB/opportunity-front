package br.ufpb.dcx.oppfyhub.opportunityhub.service;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.LoginUserDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.dto.ResponseLoginDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.InvalidLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationIntermediary {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    public ResponseLoginDTO authenticate(LoginUserDTO usuario) {
        if (!userService.validateUserPassword(usuario)) {
            throw new InvalidLoginException();
        }

        String token = jwtService.generateToken(usuario.getEmail());
        return new ResponseLoginDTO(token);
    }
}
