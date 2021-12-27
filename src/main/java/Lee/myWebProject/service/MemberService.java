package Lee.myWebProject.service;

import Lee.myWebProject.domain.Member;
import Lee.myWebProject.repository.MemberRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member;
    }

    public Long doLogin(String userId, String password){
        Member loginMember = memberRepository.findByUserId(userId, password);
        return loginMember.getId();
    }

    private void validateDuplicateMember(Member member) {

    }


}
