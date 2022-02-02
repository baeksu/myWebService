package Lee.myWebProject.web.member;


import Lee.myWebProject.domain.bulletinBoard.PostService;
import Lee.myWebProject.domain.member.Member;
import Lee.myWebProject.domain.member.MemberService;
import Lee.myWebProject.domain.bulletinBoard.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;


@Slf4j
@Controller
@RequestMapping//localhost:8080 일 때 맨처음 화면
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;//이거 더미테스트 넣으려고 임시로 넣어둔거다



    /**
     * 테스트용 데이터 하나 넣어두자
     */
    @PostConstruct
    public void init(){
        Member member = new Member();
        member.setUserName("백수");
        member.setLoginId("baeksu");
        member.setPassword("123");
        member.setEmail("baeksu@ex.com");

        memberService.join(member);

        for(Long i = 1L ; i<= 100 ; i++){
            Post dumyPost = new Post(String.valueOf(i), i, "", member);
            postService.savePost(dumyPost);
        }


        log.info("+++++++더미 데이터 생성 완료!!!+++++++++");
    }

    /**
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     **/



    /**
     * index.html에서 회원가입을 누르면 회원가입 양식폼을 반환 할거다.
     * 이떄 Model 에 MemberForm 을 담아서 보내준다.
     */
    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";// @Controller 면서 String 을 반환하면 view의 논리적 이름이 된다.
    }

    /**
     * 회원가입 폼 작성 후 제출을 누르면 회원가입을 완료한다.
     */
    @PostMapping("members/new")
    public String createMember(@Validated @ModelAttribute MemberForm memberForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError err : allErrors) {
                log.info("error={}", err);
            }
            return "members/createMemberForm";
        }

        Member member = new Member(
                memberForm.getUserId(),
                memberForm.getUserName(),
                memberForm.getPassword(),
                memberForm.getEmail()
        );
        memberService.join(member);

        //뭔가 회원가입 완료 메세지를 팝업창으로 띄워주고 home화면으로 보내고 싶은데
        return "/home";
    }




}


