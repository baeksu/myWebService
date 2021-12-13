package Lee.myWebProject.service;

import Lee.myWebProject.domain.Member;
import Lee.myWebProject.repository.MemberRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter @Getter
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public void join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);

    }

    private void validateDuplicateMember(Member member) {
        Member isAlreadyMember = memberRepository.findById(member.getId());
        if(isAlreadyMember != null ) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


}
