package Lee.myWebProject.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long idNum;
    private String name;
    private String id;
    private String password;

    public Member() {
    }

    public Member(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }
}
