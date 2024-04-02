package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

public class InvalidTokenException extends ErrorLoginException{
    public InvalidTokenException() {
        super("Invalid Token", "Missing or badly formatted token");
    }
}
