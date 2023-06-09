package app.serv.service.implementation;

import app.serv.dto.PostDTO;
import app.serv.model.Post;
import app.serv.repository.PostRepository;
import app.serv.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post findPostByContent(String content) {
        Optional<Post> post = postRepository.findPostByContent(content);
        if(!post.isEmpty()){
            return post.get();
        }

        return null;
    }

    @Override
    public Post findPostById(Integer id) {
        Optional<Post> post = postRepository.findPostById(id);
        if(!post.isEmpty()){
            return post.get();
        }

        return null;
    }

    @Override
    public Post createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setCreationDate(LocalDateTime.now());
        post = postRepository.save(post);
        return post;


    }

    @Override
    public List<Post> findAll() {return this.postRepository.findAll();}

    @Override
    public void save(Post post) {
        this.postRepository.save(post);
    }

    @Override
    public void delete(Integer id) {
        this.postRepository.deleteById(id);
    }
}
