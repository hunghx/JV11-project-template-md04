package ra.project.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserResponse {
    private String name;
    private String email;
    private Long id;
//    private Collection<? extends GrantedAuthority> authorities;
}
