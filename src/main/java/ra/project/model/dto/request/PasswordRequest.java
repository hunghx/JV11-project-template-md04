package ra.project.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequest {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
