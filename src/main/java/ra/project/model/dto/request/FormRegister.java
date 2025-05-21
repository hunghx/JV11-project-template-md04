package ra.project.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormRegister {
    private String email;
    private String password;
    private String name;
}
