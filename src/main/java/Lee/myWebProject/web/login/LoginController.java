package Lee.myWebProject.web.login;

import Lee.myWebProject.domain.member.Member;
import Lee.myWebProject.domain.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberRepository memberRepository;


//    @GetMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("loginForm", new LoginForm());
//        return "members/loginMemberForm";
//    }
    /**
     * id, pw 를 입력받아서 로그인 하는 화면
     */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm,
                        BindingResult result,
                        Model model,
                        HttpServletResponse response) {

        if (result.hasErrors()) {
            return "members/loginMemberForm";
        }

        Member loginMember = memberRepository.findByLoginId(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            return "members/loginMemberForm";
        }


        //쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        model.addAttribute("member", loginMember);
        return "home";
    }


}
