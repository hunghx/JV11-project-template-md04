package ra.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ra.project.model.dto.request.PasswordRequest;
import ra.project.model.dto.request.UserUpdateRequest;
import ra.project.model.dto.response.UserInfo;
import ra.project.model.entity.User;
import ra.project.model.entity.UserVocabulary;
import ra.project.model.entity.Vocabulary;
import ra.project.repository.UserRepository;
import ra.project.repository.UserVocabularyRepository;
import ra.project.service.IUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements IUserService {
    private  final UserRepository userRepository;
    private  final UserVocabularyRepository userVocabularyRepository;
    private  final ModelMapper mapper;
    @Override
    public UserInfo getCurrentUser(Long userId) {
        User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return UserInfo.builder()
                .id(u.getId())
                .email(u.getEmail())
                .name(u.getName())
                .createdAt(u.getCreatedAt())
                .build();
    }

    @Override
    public UserInfo updateCurrentUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        if (request.getName() != null) {
            user.setName(request.getName());
        }
        User updatedUser = userRepository.save(user);
        return UserInfo.builder()
                .id(updatedUser.getId())
                .email(updatedUser.getEmail())
                .name(updatedUser.getName())
                .createdAt(updatedUser.getCreatedAt())
                .build();
    }

    @Override
    public void changePassword(PasswordRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!request.getNewPassword().equals(request.getOldPassword())) {
            throw new RuntimeException("Passwords is equals old password");
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }

    @Override
    public List<Vocabulary> getVocabulariesForCurrentUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userVocabularyRepository.findByUser(user).stream().map(UserVocabulary::getVocabulary).toList();
    }
}
