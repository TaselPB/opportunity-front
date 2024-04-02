package br.ufpb.dcx.oppfyhub.opportunityhub.controller;

import br.ufpb.dcx.oppfyhub.opportunityhub.dto.DetailsProblemDTO;
import br.ufpb.dcx.oppfyhub.opportunityhub.execption.HTTPErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

@RestControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetailsProblemDTO> handlerGeneralError(ServletWebRequest request, Exception err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .detail(err.getMessage())
                .title(err.getCause().getMessage())
                .build();
        return new ResponseEntity<>(detailsProblemDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HTTPErrorException.class)
    public ResponseEntity<DetailsProblemDTO> handlerHTTPError(ServletWebRequest request, HTTPErrorException err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(err.getStatusCode().value())
                .detail(err.getDetails())
                .title(err.getTitle())
                .build();
        return new ResponseEntity<>(detailsProblemDTO, err.getStatusCode());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<DetailsProblemDTO> handlerHttpRequestMethodNotSupportedException(ServletWebRequest request, HttpRequestMethodNotSupportedException err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(err.getStatusCode().value())
                .detail(err.getMessage().toString())
                .title(err.getTitleMessageCode().toString())
                .build();
        return new ResponseEntity<>(detailsProblemDTO, err.getStatusCode());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<DetailsProblemDTO> handlerMissingServletRequestParameterException(ServletWebRequest request, MissingServletRequestParameterException err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(err.getStatusCode().value())
                .detail(err.getMessage().toString())
                .title(err.getTitleMessageCode().toString())
                .build();
        return new ResponseEntity<>(detailsProblemDTO, err.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DetailsProblemDTO> handleMethodArgumentNotValid(ServletWebRequest request, MethodArgumentNotValidException err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(err.getStatusCode().value())
                .detail("Incorrect Method argument. Please verify your argument"+err.getFieldError())
                .title(err.getTitleMessageCode().toString())
                .build();
        return new ResponseEntity<>(detailsProblemDTO, err.getStatusCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<DetailsProblemDTO> handlerHttpMessageNotReadableException(ServletWebRequest request, HttpMessageNotReadableException err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .detail("Http message error, please verify"+err.getMessage())
                .title(err.getHttpInputMessage().toString())
                .build();
        return new ResponseEntity<>(detailsProblemDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    ResponseEntity<DetailsProblemDTO> handlerMissingRequestHeaderExceptionException(ServletWebRequest request, MissingRequestHeaderException err) {
        DetailsProblemDTO detailsProblemDTO = DetailsProblemDTO
                .builder()
                .type(request.getRequest().getRequestURL().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(err.getMessage().toString())
                .title(err.getTitleMessageCode().toString())
                .build();

        return new ResponseEntity<>(detailsProblemDTO, HttpStatus.BAD_REQUEST);
    }
}
