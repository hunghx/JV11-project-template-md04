package ra.project.service;

import ra.project.model.dto.request.PasswordRequest;
import ra.project.model.dto.request.UserUpdateRequest;
import ra.project.model.dto.response.UserInfo;
import ra.project.model.entity.Vocabulary;

import java.util.List;

public interface IUserService {
    UserInfo getCurrentUser(String email);
    UserInfo updateCurrentUser(UserUpdateRequest request, String email);
    void changePassword(PasswordRequest request, String email);
    List<Vocabulary> getVocabulariesForCurrentUser(String email);
}
