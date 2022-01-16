package Lee.myWebProject.domain.bulletinBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 게시글 저장
     */
    @Transactional
    public void savePost(Post post){
        postRepository.save(post);
    }

    /**
     * 게시글 출력
     */
    public List<Post> getAllPost(Integer idx){
        return postRepository.getAll(idx);
    }

}
