package alura.challenge.forohub.infrastructure.adapter.in.rest.exceptionhandler;

import alura.challenge.forohub.application.exceptions.*;
import alura.challenge.forohub.domain.exceptions.BlankContentException;
import alura.challenge.forohub.domain.exceptions.BlankTitleException;
import alura.challenge.forohub.domain.exceptions.ClosedTopicException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e){
        return ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BlankContentException.class)
    public ProblemDetail handleBlankContentException(BlankContentException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
    @ExceptionHandler(BlankTitleException.class)
    public ProblemDetail handleBlankTitleException(BlankTitleException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
    @ExceptionHandler(ClosedTopicException.class)
    public ProblemDetail handleClosedTopicException(ClosedTopicException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }
    @ExceptionHandler(DuplicatedTopicException.class)
    public ProblemDetail handleBlankContentException(DuplicatedTopicException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(DuplicatedResponseException.class)
    public ProblemDetail handleDuplicatedResponseException(DuplicatedResponseException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(RegisteredUserException.class)
    public ProblemDetail handleRegisteredUserException(RegisteredUserException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ProblemDetail handleTokenExpiredException(TokenExpiredException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ProblemDetail handleUnauthorizedUserException(UnauthorizedUserException e){
        return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException e){
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validations errors were found.");

        Map<String, String> violations = e.getConstraintViolations().stream()
            .collect(Collectors.toMap(violation -> violation.getPropertyPath()
                            .toString()
                            .substring(violation.getPropertyPath()
                                    .toString()
                                    .lastIndexOf(".") + 1
                            ),
                    ConstraintViolation::getMessage)
            );

        problem.setProperty("errors", violations);

        return problem;
    }

}
