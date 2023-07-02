package app.serv.service.implementation;

import app.serv.dto.PostDTO;
import app.serv.model.Post;
import app.serv.repository.PostRepository;
import app.serv.service.PostService;
import app.serv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public Post findPostById(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new NullPointerException("Post not found with id: " + id));
    }

    @Override
    public Post createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setCreationDate(LocalDateTime.now());
        post.setUser(userService.findLoggedUser());
        post.setIsDeleted(false);
        post = postRepository.save(post);
        return post;
    }

    @Override
    public void delete(Integer id){
        Post post = this.findPostById(id);
        post.setIsDeleted(true);
        this.save(post);
    }

    @Override
    public List<Post> findAllByUser(Integer userId){
        return null;
    }

    @Override
    public  List<Post> findAllforContent(String username){
        return null;
    }

    @Override
    public List<Post> findAll() {return this.postRepository.findAll();}

    @Override
    public List<PostDTO> findAllView(){
        List<Post> posts = findAll();
        List<PostDTO> ret = new ArrayList<>();
        for (Post p : posts){
            ret.add(new PostDTO(p));
        }
        return ret;
    }

    @Override
    public void save(Post post) {
        this.postRepository.save(post);
    }

}
