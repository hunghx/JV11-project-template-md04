package ra.project.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Builder
@Getter
@Setter
public class JwtResponse {
    private String accessToken;
    private final String type = "Bearer";
    private String username;
    private Long id;
//    private Collection<? extends GrantedAuthority> authorities;
}
