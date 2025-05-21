package ra.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ra.project.model.dto.request.PasswordRequest;
import ra.project.model.dto.request.UserUpdateRequest;
import ra.project.model.dto.response.UserInfo;
import ra.project.model.entity.Vocabulary;
import ra.project.service.IUserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    @GetMapping("/me")
    public ResponseEntity<UserInfo> getCurrentUser(@AuthenticationPrincipal UserDetails details) {
        return ResponseEntity.ok(userService.getCurrentUser(details.getUsername()));
    }

    @PutMapping("/me")
    public ResponseEntity<UserInfo> updateCurrentUser(@RequestBody UserUpdateRequest request, @AuthenticationPrincipal UserDetails details) {
        return ResponseEntity.ok(userService.updateCurrentUser(request, details.getUsername()));
    }
    @PutMapping("/me/password")
    public ResponseEntity<Map<String,String>> updatePassword(@RequestBody PasswordRequest request, @AuthenticationPrincipal UserDetails details) {
        // Implement password update logic here
        userService.changePassword(request, details.getUsername());
        return ResponseEntity.ok(Map.of("message", "Đổi mật khẩu thành công"));
    }

    @GetMapping("/me/vocabularies")
    public ResponseEntity<List<Vocabulary>> getVocabulariesForCurrentUser(@AuthenticationPrincipal UserDetails details) {
        // Implement logic to fetch vocabularies by topic ID
        return ResponseEntity.ok(userService.getVocabulariesForCurrentUser(details.getUsername()));
    }

}
