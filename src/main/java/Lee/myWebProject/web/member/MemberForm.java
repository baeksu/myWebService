package Lee.myWebProject.web.member;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class MemberForm {

    public MemberForm() {
    }

    @NotBlank
    private String userId;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private String email;
}
