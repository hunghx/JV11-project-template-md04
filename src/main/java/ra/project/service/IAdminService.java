package ra.project.service;

import ra.project.model.dto.response.UserResponseDto;

public interface IAdminService {
    UserResponseDto addRoleToUser(Long userId, Long roleId);
}
