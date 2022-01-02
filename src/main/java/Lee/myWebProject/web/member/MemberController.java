package Lee.myWebProject.web.member;


import Lee.myWebProject.domain.member.Member;
import Lee.myWebProject.domain.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


@Slf4j
@Controller
@RequestMapping//localhost:8080 일 때 맨처음 화면
public class MemberController {

    private final MemberService memberService;

    @Autowired//MemberService 를 생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

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
    @ResponseBody
    @PostMapping("members/new")
    public String createMember(MemberForm memberForm){
        Member member = new Member(
                memberForm.getUserId(),
                memberForm.getUserName(),
                memberForm.getPassword(),
                memberForm.getEmail()
        );

        memberService.join(member);

        return "회원 가입 완료!!";
    }




}


