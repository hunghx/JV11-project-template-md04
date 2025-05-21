package ra.project.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Builder
@Getter
@Setter
public class UserInfo {
    private String name;
    private String email;
    private Long id;
    private Date createdAt;
}
