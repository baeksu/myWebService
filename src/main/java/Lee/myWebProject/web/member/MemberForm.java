package Lee.myWebProject.web.member;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    public MemberForm() {
    }

    private String userId;
    private String userName;
    private String password;
    private String email;
}
