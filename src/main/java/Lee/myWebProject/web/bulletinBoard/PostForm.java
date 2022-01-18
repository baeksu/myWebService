package Lee.myWebProject.web.bulletinBoard;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter
public class PostForm {
    private Long memberId;
    private String title;
    private String content;



}
