package Lee.myWebProject.controller;

import Lee.myWebProject.domain.Member;
import Lee.myWebProject.service.MemberService;
import lombok.Data;
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
     * 초기데이터 입력
     */
    @PostConstruct
    public void init(){
        memberService.join(new Member("경우", "kyungu", "123"));
    }
    /**+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++**/

    @ResponseBody
    @GetMapping("/login")
    public String login(@ModelAttribute Member member, Model model) {
        if (memberService.getMemberRepository().findById(member.getId()) == null){
            return "login 실패!!";
        }
        log.info("member.name={}", member.getName());
        log.info("member.id={}", member.getId());
        log.info("member.password={}", member.getPassword());

        return "login 성공!!";
    }

    @GetMapping("/join")
    public String joinMember(){
        return "/memberJoin";// @Controller 면서 String 을 반환하면 view의 논리적 이름이 된다.
    }

    @ResponseBody
    @PostMapping("/join")
    public String addMember(@ModelAttribute Member member, Model model) {
        model.addAttribute(member);
        if(memberService.join(member) == null){
            return "중복된 아이디 입니다.";
        }

        return "저장 성공!";
    }


}
