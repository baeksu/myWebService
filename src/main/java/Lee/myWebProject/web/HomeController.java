package Lee.myWebProject.web;

import Lee.myWebProject.domain.member.Member;
import Lee.myWebProject.domain.member.MemberRepository;
import Lee.myWebProject.domain.member.MemberService;
import Lee.myWebProject.web.bulletinBoard.PostForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final MemberService memberService;

    @RequestMapping("/")
    public String homeLogin(@SessionAttribute(name = AddtionalInfoInterface.SESSION_COOKIE_NAME, required = false)Member loginMember,
                            Model model) {

        if (loginMember == null) {return "home";}

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
