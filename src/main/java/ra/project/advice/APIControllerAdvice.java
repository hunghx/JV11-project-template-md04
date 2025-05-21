package ra.project.advice;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.mail.MessagingException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.project.exception.AuthenticationException;
import ra.project.exception.NotFoundException;
import ra.project.model.dto.response.ErrorResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
@Hidden
public class APIControllerAdvice {
    // Bắt va xu lý lỗi NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String,ErrorResponse>> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage());
        ErrorResponse err = new ErrorResponse();
        err.setCode(404);
        err.setMessage(e.getMessage());
        err.setDetails("");
        Map<String,ErrorResponse> map = new HashMap<>();
        map.put("error", err);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,ErrorResponse>> handleMethodArgumentException(MethodArgumentNotValidException e){
        log.error(e.getMessage());
        Map<String, String> details = new HashMap<>();
        e.getFieldErrors().forEach(fieldError -> {
            details.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        ErrorResponse err = new ErrorResponse();
        err.setCode(400);
        err.setMessage("Invalid Parameter");
        err.setDetails(details);
        Map<String,ErrorResponse> map = new HashMap<>();
        map.put("error", err);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String,ErrorResponse>> handleAuthentication(AuthenticationException e){
        log.error(e.getMessage());
        ErrorResponse err = new ErrorResponse();
        err.setCode(400);
        err.setMessage(HttpStatus.UNAUTHORIZED.name());
        err.setDetails(e.getMessage());
        Map<String,ErrorResponse> map = new HashMap<>();
        map.put("error", err);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<?> handleSendmail(MessagingException e){
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    // Lỗi conflitch Du liệu áp dụng cho ràng buộc Unique:
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,ErrorResponse>> handleConflictData(DataIntegrityViolationException e) {
        log.error(e.getMessage());
        ErrorResponse err = new ErrorResponse();
        err.setCode(409);
        err.setMessage(HttpStatus.CONFLICT.name());
        err.setDetails(e.getMessage());
        Map<String,ErrorResponse> map = new HashMap<>();
        map.put("error", err);
        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }

}
