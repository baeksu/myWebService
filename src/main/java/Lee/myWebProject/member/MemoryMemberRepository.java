package Lee.myWebProject.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    /**
     * db는 이후에 jpa를 통해서 저장할거다
     * 우선은 메모리에 저장하는걸로 테스트 진행할 거다.
     */
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getIdNum(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
