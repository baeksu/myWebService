package Lee.myWebProject.repository;

import Lee.myWebProject.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class MemoryMemberRepository implements MemberRepository {
    /**
     * db는 이후에 jpa를 통해서 저장할거다
     * 우선은 메모리에 저장하는걸로 테스트 진행할 거다.
     */
    private static Map<String, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public void save(Member member) {
        member.setIdNum(++sequence);
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(String id) {
        return store.get(id);
    }
}
