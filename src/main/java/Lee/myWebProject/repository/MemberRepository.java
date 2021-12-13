package Lee.myWebProject.repository;


import Lee.myWebProject.domain.Member;

public interface MemberRepository {
    /**
     * 멤버 객체 저장 관련 인터페이스
     * 기능 : 저장, 조회
     */

    void save(Member member);

    Member findById(String id);
}
