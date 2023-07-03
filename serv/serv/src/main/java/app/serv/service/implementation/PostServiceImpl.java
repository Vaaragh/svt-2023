package app.serv.service.implementation;

import app.serv.dto.PostDTO;
import app.serv.model.Post;
import app.serv.model.Reaction;
import app.serv.repository.PostRepository;
import app.serv.repository.ReactionRepository;
import app.serv.service.PostService;
import app.serv.service.ReactionService;
import app.serv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    ReactionRepository reactionRepository;
    UserService userService;
    @Autowired
    public PostServiceImpl(ReactionRepository reactionRepository, PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.reactionRepository = reactionRepository;
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
    public List<Post> findAll() {return this.postRepository.findAll();}

    @Override
    public List<PostDTO> findAllView(){
        Set<Post> posts = postRepository.getAllExisting();
        List<PostDTO> ret = new ArrayList<>();
        for (Post p : posts){
            PostDTO newP = new PostDTO(p);
            newP.setReactions(getAllByPost(p));
            ret.add(newP);

        }
        return ret;
    }

    @Override
    public void save(Post post) {
        this.postRepository.save(post);
    }

    private Map<String, Integer> getAllByPost(Post post) {
        Set<Reaction> reactions = this.reactionRepository.findByPost(post);
        Map<String,Integer> ret = new HashMap<>();
        ret.put("HEART", 0);
        ret.put("LIKE", 0);
        ret.put("DISLIKE", 0);
        for (Reaction r : reactions){
            ret.put(r.getType().toString(),ret.get(r.getType().toString())+1);
        }
        return ret;
    }

}
