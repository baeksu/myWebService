package Lee.myWebProject.domain.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);

    }

    public Member findByLoginId(String userId , String password) {

        try{
            return em.createQuery("select m from Member m where m.loginId = :userId and m.password = :password", Member.class)
                    .setParameter("userId", userId)
                    .setParameter("password", password)
                    .getSingleResult();
        }catch(NoResultException e){
            //조회가 안되면 null을 반환해주자
            return null;
        }
    }

    public Member findById(Long id){
        try{
            return em.createQuery("select m from Member m where m.id = :id",Member.class)
                    .setParameter("id",id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
