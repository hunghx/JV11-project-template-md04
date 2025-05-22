package ra.project.service;

import ra.project.model.dto.request.PasswordRequest;
import ra.project.model.dto.request.UserUpdateRequest;
import ra.project.model.dto.response.UserInfo;
import ra.project.model.entity.Vocabulary;

import java.util.List;

public interface IUserService {
    UserInfo getCurrentUser(Long userId);
    UserInfo updateCurrentUser(UserUpdateRequest request);
    void changePassword(PasswordRequest request);
    List<Vocabulary> getVocabulariesForCurrentUser(Long userId);
}
