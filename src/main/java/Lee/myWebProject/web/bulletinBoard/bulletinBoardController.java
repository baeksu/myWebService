package Lee.myWebProject.web.bulletinBoard;

import Lee.myWebProject.domain.bulletinBoard.Post;
import Lee.myWebProject.domain.bulletinBoard.PostService;
import Lee.myWebProject.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class bulletinBoardController {

    private final PostService postService;

    @GetMapping("/bulletinBoard")
    public String board(Model model) {
        //이때 10개씩 게시글을 넘겨주고 싶은데... 어떻게 해야할까...
        List<Post> posts = postService.getAllPost(0);

        model.addAttribute("posts", posts);
        return "bulletinBoard";
    }


}
