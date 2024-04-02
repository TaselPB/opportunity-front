package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

public class UserAlreadyExistsException extends AlreadyExistsException{
    public UserAlreadyExistsException() {
        super("User already exists", "User already registered in the system");
    }
}
