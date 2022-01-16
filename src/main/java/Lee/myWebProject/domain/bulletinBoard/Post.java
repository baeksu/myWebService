//게시글 정보

package Lee.myWebProject.domain.bulletinBoard;

import Lee.myWebProject.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private String title;
    private Long viewCnt;
    private String content;

    /**
     * 회원들이 게시글을 작성을 하니까 회원 : 게시글 = 1 : N 관계
     * 게시글 엔티티에 ManyToOne 관계를 설정해주자. 우선은 단방향으로 설정해주자.
     */
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Post() {}

    public Post(String title, Long viewCnt, String content, Member member) {
        this.title = title;
        this.viewCnt = viewCnt;
        this.content = content;
        this.member = member;
    }
}
