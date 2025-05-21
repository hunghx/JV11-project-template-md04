package ra.project.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.project.exception.AuthenticationException;
import ra.project.security.jwt.JwtProvider;
import ra.project.model.dto.request.FormLogin;
import ra.project.model.dto.request.FormRegister;
import ra.project.model.dto.response.UserResponse;
import ra.project.model.entity.User;
import ra.project.repository.UserRepository;
import ra.project.security.principle.UserDetailsCustom;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final JwtProvider jwtProvider;
    public Map<String, Object> login(FormLogin request) {
        Authentication auth = null;
        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new AuthenticationException("Email hoặc mật khẩu không đúng");
        }
        UserDetailsCustom userDetails = (UserDetailsCustom) auth.getPrincipal();
        return Map.of(
                "message", "Đăng nhập thành công",
                "token", jwtProvider.generateToken(request.getEmail()),
                "user", UserResponse.builder()
                        .id(userDetails.getId())
                        .email(userDetails.getUsername())
                        .name(userDetails.getName())
                        .build()
        );
    }

    public Map<String, Object> register(FormRegister request) {
        User user = mapper.map(request, User.class);
        // mã hóa mật khẩu
//        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(12)));
        user.setPassword(encoder.encode(request.getPassword()));
        User result = userRepository.save(user);
        return Map.of(
                "message", "Đăng ký thành công",
                "user", UserResponse.builder()
                        .id(result.getId())
                        .email(result.getEmail())
                        .name(result.getName())
                        .build()
        );
    }
}
