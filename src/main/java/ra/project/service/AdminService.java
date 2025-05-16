package ra.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.project.exception.NotFoundException;
import ra.project.exception.RoleExistException;
import ra.project.model.dto.response.UserResponseDto;
import ra.project.model.entity.Role;
import ra.project.model.entity.User;
import ra.project.repository.IRoleRepository;
import ra.project.repository.IUserRepository;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService{
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    @Override
    public UserResponseDto addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user ko tim thay"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("role ko tim thay"));


        // them quyen
        boolean check = user.getRoleSet().stream().anyMatch(r-> r.getRoleName()==role.getRoleName());
        if (check){
            throw new RoleExistException("Người dùng đã có quyên này");
        }
        user.getRoleSet().add(role);
        User userUpdate= userRepository.save(user);
        UserResponseDto dto = UserResponseDto.builder()
                .id(userUpdate.getId())
                .address(userUpdate.getAddress())
                .phone(userUpdate.getPhone())
                .email(userUpdate.getEmail())
                .avatar(userUpdate.getAvatar())
                .updatedAt(userUpdate.getUpdatedAt())
                .createdAt(userUpdate.getCreatedAt())
                .roleSet(userUpdate.getRoleSet())
                .isDeleted(userUpdate.isDeleted())
                .status(userUpdate.isStatus())
                .fullName(userUpdate.getFullName())
                .username(userUpdate.getUsername())
                .build();
        // chuển đổi từ Entity về DTO
        return dto;
    }
}
