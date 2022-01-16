package Lee.myWebProject.domain.member;

import Lee.myWebProject.domain.member.Member;
import Lee.myWebProject.domain.member.MemberRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Setter @Getter
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }

    /**
     * 회원 가입
     */
    @Transactional
    public Member join(Member member) {
        memberRepository.save(member);
        return member;
    }


}
