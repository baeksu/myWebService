package Lee.myWebProject.domain.login;

import Lee.myWebProject.domain.member.Member;
import Lee.myWebProject.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class loginService {
    private final MemberRepository memberRepository;

    /**
     * loginId, password 비교하여 로그인
     */
    public Member login(String loginId, String password){
        return memberRepository.findByLoginId(loginId, password);
    }

}
