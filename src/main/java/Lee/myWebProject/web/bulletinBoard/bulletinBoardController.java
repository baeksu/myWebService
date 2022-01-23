package Lee.myWebProject.web.bulletinBoard;

import Lee.myWebProject.domain.bulletinBoard.Post;
import Lee.myWebProject.domain.bulletinBoard.PostService;
import Lee.myWebProject.domain.member.Member;
import Lee.myWebProject.domain.member.MemberRepository;
import Lee.myWebProject.domain.member.MemberService;
import Lee.myWebProject.web.AddtionalInfoInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//스프링 공부 필요, 싱글톤 빈으로 등록되어있을떄 두명의 사용자가 게시글 작성을 하면 충돌발생 안하나?
@Controller
@RequiredArgsConstructor
@Slf4j
public class bulletinBoardController {

    private final PostService postService;
    private final MemberService memberService;


    @GetMapping("/bulletinBoard")
    public String board(@SessionAttribute(name = AddtionalInfoInterface.SESSION_COOKIE_NAME, required = false)Member loginMember,
                        @RequestParam(defaultValue = "1") int page,Model model, HttpServletRequest request ) {

        List<Post> posts = postService.getAllPost(page);
        setModelAboutPost(model, posts, page);

        //로그인 멤버를 세션에서 가져온다
        if (loginMember == null) {
            return "bulletinBoard/bulletinBoard";
        } else {
            model.addAttribute(loginMember);
            return "bulletinBoard/loginBulletinBoard";
        }
    }


    @GetMapping("/bulletinBoard/new")
    public String add(Model model, HttpServletRequest request) {

        //로그인 여부 체크 구현해야함
        HttpSession session = request.getSession();
        if (session == null) {
            return "/bulletinBoard/bulletinBoard";
        }

        //글작성
        model.addAttribute("postForm", new PostForm());
        return "bulletinBoard/addPost";
    }

    @PostMapping("/bulletinBoard/new")
    public String savePost(@ModelAttribute PostForm postForm, HttpServletRequest request, Model model) {

        //아니 근데 멤버 넣어줄때 쿠키를 매번 조회를 해야하나...
        //어디 한곳에다가 쿠키정보를 저장해둬야하나...
        postService.savePost(new Post(postForm.getTitle(),
                0L,
                postForm.getContent(),
                memberService.findMemberById(Long.parseLong(request.getCookies()[0].getValue()))));

        //여기서는 redirect를 해줘야지... 왜냐? 해당 url로 get요청이 필요하다. 페이징 + 게시글 출력때문에
        return "redirect:/bulletinBoard";
    }


    @GetMapping("/bulletinBoard/search")
    public String searchPost(@RequestParam String searchContent,@RequestParam(defaultValue = "1") int page, Model model) {
        log.info(searchContent + " " + page);

        List<Post> posts = postService.findPostByKeyword(searchContent,page-1);

        //1~10 , 11~20 , ... 시작 페이지 번호를 계산해서 넘겨주자
        setModelAboutSearchPost(model,posts,page,searchContent);
        return "bulletinBoard/searchKeywordResult";
    }

    private void setModelAboutSearchPost(Model model, List<Post> posts, int page, String searchContent) {
        //시작 페이지 구역 1 = 1~10 , 2= 11~20
        int startPage = (page-1)/10 + 1;
        int endPage = startPage+9;

        model.addAttribute("page", page);
        model.addAttribute("posts", posts);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchContent", searchContent);
    }

    private void setModelAboutPost(Model model, List<Post> posts, int page) {
        //시작 페이지 구역 1 = 1~10 , 2= 11~20
        int startPage = (page-1)/10 + 1;
        int endPage = startPage+9;

        model.addAttribute("page", page);
        model.addAttribute("posts", posts);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
    }





}
