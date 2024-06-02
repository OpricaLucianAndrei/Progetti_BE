package it.epicode.gestioneEventi.exceptions;

import it.epicode.gestioneEventi.entity.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CentralizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> BadRequestHandler(BadRequestException e){
        Error error= new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> UnathorizedHandler(UnauthorizedException e){
        Error error= new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNonTrovatoException.class)
    public ResponseEntity<Object> UserNotFoundHandler(UserNonTrovatoException e){
        Error error= new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventoNonTrovatoException.class)
    public ResponseEntity<Object> UserNotFoundHandler(EventoNonTrovatoException e){
        Error error= new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NumeroMassimoPartecipantiRaggiunto.class)
    public ResponseEntity<Object> UserNotFoundHandler(NumeroMassimoPartecipantiRaggiunto e){
        Error error= new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(PostiEsauritiException.class)
    public ResponseEntity<Object> UserNotFoundHandler(PostiEsauritiException e){
        Error error= new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.PRECONDITION_FAILED);

        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }
}
