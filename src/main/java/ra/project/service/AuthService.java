package ra.project.service;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import ra.project.exception.AuthenticationException;
import ra.project.jwt.JwtProvider;
import ra.project.model.dto.request.FormLogin;
import ra.project.model.dto.request.FormRegister;
import ra.project.model.dto.response.UserResponse;
import ra.project.model.entity.User;
import ra.project.repository.UserRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final JwtProvider jwtProvider;
    public Map<String, Object> login(FormLogin request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthenticationException("Tài khoản không tồn tại"));
        if (request.getPassword() ==null || !BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Mật khẩu không chính xác");
        }
        return Map.of(
                "message", "Đăng nhập thành công",
                "token", jwtProvider.generateToken(request.getEmail()),
                "user", UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .build()
        );
    }

    public Map<String, Object> register(FormRegister request) {
        User user = mapper.map(request, User.class);
        // mã hóa mật khẩu
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(12)));
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
