package br.ufpb.dcx.oppfyhub.opportunityhub.execption;

public class NotFoundJobException extends NotFoundException{
    public NotFoundJobException(){
        super("Job not found", "Job not registered in the system");
    }
}
