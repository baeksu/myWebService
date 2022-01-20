package Lee.myWebProject.domain.bulletinBoard;

import Lee.myWebProject.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostRepository{
    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    /**
     * 10개씩 끊어서 가지고 오자
     * @return
     */
    public List<Post> getAll(Integer idx){
        List<Post> result = em.createQuery("select p from Post p order by p.id desc", Post.class)
                .setFirstResult(idx*10)
                .setMaxResults(10)
                .getResultList();

        return result;
    }


    public List<Post> findPostByKeyword(String keyword,Integer idx) {
        List<Post> result = em.createQuery("select p from Post p where p.title LIKE CONCAT('%',:keyword ,'%')", Post.class)
                .setParameter("keyword",keyword)
                .setFirstResult(idx*10)
                .setMaxResults(10)
                .getResultList();

        return result;
    }
}
