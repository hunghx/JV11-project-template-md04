package ra.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.project.model.dto.request.FormLogin;
import ra.project.model.dto.request.FormRegister;
import ra.project.model.dto.response.UserResponse;
import ra.project.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, Object>> signIn(@RequestBody FormLogin request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }
    @PostMapping("/sign-up")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody FormRegister request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

}
