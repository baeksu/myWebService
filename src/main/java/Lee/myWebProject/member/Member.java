package Lee.myWebProject.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long idNum;
    private String name;
    private String id;
    private String password;

    public Member(Long idNum, String name, String id, String password) {
        this.idNum = idNum;
        this.name = name;
        this.id = id;
        this.password = password;
    }
}
