package Lee.myWebProject.controller;


import Lee.myWebProject.domain.Member;
import Lee.myWebProject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;


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
     * 초기데이터 입력
     */
    @PostConstruct
    public void init(){
        Member member = new Member();
        member.setUserName("백수");
        member.setUserId("baeksu");
        member.setPassword("123");
        member.setEmail("baeksu@ex.com");

        memberService.join(member);
    }

    /**
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     **/

    /**
     * id, pw 를 입력받아서 로그인 하는 화면
     */
    @ResponseBody
    @PostMapping("/login")
    public String login(@ModelAttribute Member member) {
        //@ModelAttribute로 해도 값이 넘어오네? 스프링이 알아서 html name이름을 보고 해당
        // Member의 set을 호출하는건가? -> get,post 처리하는거좀 블로그에 정리한번 하자
        Long memberId = memberService.doLogin(member.getUserId(), member.getPassword());
        log.info("로그인 memberId = " + memberId);

        return memberId != null ? "login 성공" : "login 실패";
    }

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
     *
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
