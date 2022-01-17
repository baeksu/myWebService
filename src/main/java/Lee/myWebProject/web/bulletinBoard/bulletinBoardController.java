package Lee.myWebProject.web.bulletinBoard;

import Lee.myWebProject.domain.bulletinBoard.Post;
import Lee.myWebProject.domain.bulletinBoard.PostService;
import Lee.myWebProject.domain.member.Member;
import Lee.myWebProject.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class bulletinBoardController {

    private final PostService postService;
    private final MemberRepository memberRepository;//이거 글쓰기할때 멤버정보 조회하려고 넣었는데 이렇게 하는게 맞나?

    @GetMapping("/bulletinBoard/{page}")
    public String board(@PathVariable Integer page, Model model) {
        //이때 10개씩 게시글을 넘겨주고 싶은데... 어떻게 해야할까...
        /**
         * 이거 지금 맨처음 자유게시판 클릭했을떄 pathvariable로 0을 초기값 넘겨준다.
         * 따로 페이징을 관리하는 클래스를 생성해야하나?
         */
        List<Post> posts = postService.getAllPost(page);

        model.addAttribute("posts", posts);
        return "bulletinBoard/bulletinBoard";
    }

    //글쓰기 클릭
    @GetMapping("/bulletinBoard/new")
    public String add(Model model, HttpServletRequest request) {

        //로그인 여부 체크
        //현재는 쿠키 적용된 버전
        Cookie[] cookies = request.getCookies();
        String cookieValue = cookies[0].getValue();

        Long memberId = Long.parseLong(cookieValue);
        Member findMember = memberRepository.findById(memberId);

        //글작성
        model.addAttribute("member", findMember);
        return "bulletinBoard/addPost";
    }

    @PostMapping("/bulletinBoard/addPost")
    public String savePost(){
        return "bulletinBoard";
    }
}
