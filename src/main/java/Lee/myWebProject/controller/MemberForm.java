package Lee.myWebProject.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Getter @Setter
public class MemberForm {

    public MemberForm() {
    }

    private String userId;
    private String userName;
    private String password;
    private String email;
}
