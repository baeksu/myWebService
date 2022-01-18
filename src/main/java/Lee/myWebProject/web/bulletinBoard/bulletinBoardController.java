package Lee.myWebProject.web.bulletinBoard;

import Lee.myWebProject.domain.bulletinBoard.Post;
import Lee.myWebProject.domain.bulletinBoard.PostService;
import Lee.myWebProject.domain.member.Member;
import Lee.myWebProject.domain.member.MemberRepository;
import Lee.myWebProject.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//스프링 공부 필요, 싱글톤 빈으로 등록되어있을떄 두명의 사용자가 게시글 작성을 하면 충돌발생 안하나?
@Controller
@RequiredArgsConstructor
@Slf4j
public class bulletinBoardController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/bulletinBoard/{page}")
    public String board(@PathVariable Integer page, Model model) {
        //이때 10개씩 게시글을 넘겨주고 싶은데... 어떻게 해야할까...
        /**
         * 이거 지금 맨처음 자유게시판 클릭했을떄 pathvariable로 1을 초기값 넘겨준다. 그래서 -1 을 해서 넘겨준다.
         * 따로 페이징을 관리하는 클래스를 생성해야하나?
         */
        List<Post> posts = postService.getAllPost(page-1);

        //1~10 , 11~20 , ... 시작 페이지 번호를 계산해서 넘겨주자
        model.addAttribute("page", page);
        model.addAttribute("posts", posts);
        return "bulletinBoard/bulletinBoard";
    }

//    private Integer calcStartPageNum(Integer page) {
//        if((page-1)%10 == 0){
//            return page;
//        }else{
//            return ((page-1)/10)*10+1;
//        }
//    }


    //글쓰기 클릭
    @GetMapping("/bulletinBoard/new")
    public String add(Model model, HttpServletRequest request) {

        //로그인 여부 체크 구현해야함
        //현재 쿠키 버전
        Cookie[] cookies = request.getCookies();
        String cookieValue = cookies[0].getValue();
        Long memberId = Long.parseLong(cookieValue);
        if (memberId == null) {
            return "bulletinBoard/addPost";
        }


        //글작성
        model.addAttribute("postForm",new PostForm());
        return "bulletinBoard/addPost";
    }

    @PostMapping("/bulletinBoard/new")
    public String savePost(@ModelAttribute PostForm postForm, HttpServletRequest request) {

        //아니 근데 멤버 넣어줄때 쿠키를 매번 조회를 해야하나...
        //어디 한곳에다가 쿠키정보를 저장해둬야하나...
        postService.savePost(new Post(postForm.getTitle(),
                                        0L,
                                        postForm.getContent(),
                                        memberService.findMemberById(Long.parseLong(request.getCookies()[0].getValue()))));

        return "bulletinBoard/bulletinBoard";
    }

}
