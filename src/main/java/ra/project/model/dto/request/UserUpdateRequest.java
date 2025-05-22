package ra.project.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateRequest {
    private Long userId;
    private String name;
}
