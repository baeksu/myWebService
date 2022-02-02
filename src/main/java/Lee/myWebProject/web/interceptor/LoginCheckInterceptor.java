package Lee.myWebProject.web.interceptor;

import Lee.myWebProject.web.AddtionalInfoInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(AddtionalInfoInterface.SESSION_COOKIE_NAME) == null) {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/login2?redirectURL="+ requestURI);//일단 홈으로 보내버리자
            return false;
        }
        return true;
    }
}
