package Lee.myWebProject.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    public Member() {
    }

    public Member(String loginId, String userName, String password, String email) {
        this.loginId = loginId;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String userName;
    private String password;
    private String email;



}
