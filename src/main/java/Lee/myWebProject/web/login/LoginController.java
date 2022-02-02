package Lee.myWebProject.web.login;

import Lee.myWebProject.domain.member.Member;
import Lee.myWebProject.domain.member.MemberRepository;
import Lee.myWebProject.web.AddtionalInfoInterface;
import jdk.jfr.Frequency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberRepository memberRepository;

    /**
     * id, pw 를 입력받아서 로그인 하는 화면
     */

    /**
     * 메인페이지에서 로그인 되지 않는 상황은 어떠한 서비스를 이용하기 위해서 로그인을 해야 하는 경우라고 생각
     * 여기서 로그인이 되면 해당 서비스로 리다이렉트 시켜주자
     */
    @GetMapping("/login2")
    public String loginForm(@ModelAttribute LoginForm form) {

        return "members/loginForm";
    }

    @PostMapping("/login2")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult result,
                        @RequestParam(defaultValue = "/") String redirectURL ,Model model,
                        HttpServletRequest request  ){
        log.info("여기까지는 오나~~?");
        log.info("redirectURL= " + redirectURL);
        if (result.hasErrors()) {
            return "/login2";
        }

        Member findMember = memberRepository.findByLoginId(loginForm.getLoginId(), loginForm.getPassword());
        if (findMember == null) {
            return "/login2";
        }

        HttpSession session = request.getSession();
        session.setAttribute(AddtionalInfoInterface.SESSION_COOKIE_NAME, findMember);
        model.addAttribute("member", findMember);
        //-> redirectAttribute로 바꿔줘야 하나?
        return "redirect:"+ redirectURL;

    }

    //이거 그냥 없애는게 나을거 같은데 메인 화면에는 로그인 버튼만 놔두고 누르면 로그인 화면으로 만드는걸로 하자
    @PostMapping("/login")
    public String mainPageLogin(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, Model model,
                        HttpServletRequest request, @RequestParam(defaultValue = "/") String redirectURL) {

        if (result.hasErrors()) {
            return "";
        }

        Member findMember = memberRepository.findByLoginId(loginForm.getLoginId(), loginForm.getPassword());

        if (findMember == null) {
            return "";
        }

        HttpSession session = request.getSession();
        session.setAttribute(AddtionalInfoInterface.SESSION_COOKIE_NAME, findMember);
        model.addAttribute("member", findMember);
        //-> redirectAttribute로 바꿔줘야 하나?
        return "";
    }


}
