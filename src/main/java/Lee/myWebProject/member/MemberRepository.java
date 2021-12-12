package Lee.myWebProject.member;


public interface MemberRepository {
    /**
     * 멤버 객체 저장 관련 인터페이스
     * 기능 : 저장, 조회
     */

    void save(Member member);

    Member findById(Long memberId);
}
