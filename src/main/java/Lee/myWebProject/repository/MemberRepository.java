package Lee.myWebProject.repository;


import Lee.myWebProject.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {

        em.persist(member);
    }

    public Member findByUserId(String userId , String password) {
        return em.createQuery("select m from Member m where m.userId = :userId and m.password = :password", Member.class)
                .setParameter("userId", userId)
                .setParameter("password", password)
                .getSingleResult();

    }
}
