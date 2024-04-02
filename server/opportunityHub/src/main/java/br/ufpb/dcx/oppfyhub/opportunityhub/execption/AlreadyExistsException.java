package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(code= HttpStatus.CONFLICT)
public class AlreadyExistsException extends HTTPErrorException{
    public AlreadyExistsException(String title, String details) {
        super(HttpStatus.CONFLICT, title, details);
    }
}
