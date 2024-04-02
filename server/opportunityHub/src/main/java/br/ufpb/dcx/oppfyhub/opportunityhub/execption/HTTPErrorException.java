package br.ufpb.dcx.oppfyhub.opportunityhub.execption;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class HTTPErrorException extends RuntimeException{
    private HttpStatus statusCode;
    private String title;
    private String details;
}
