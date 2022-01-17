package Lee.myWebProject.web;

import Lee.myWebProject.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model){

        //로그인이 된상태인걸 home 에 올떄마다 체크를 해야하나?
        //로그인 한후에 쿠키값은 있는데 home에 넘겨줄떄 member를 안넘겨주니까 로그인 화면이 나오네


        return "home";
    }
}
