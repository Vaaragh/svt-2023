package app.serv.service;

import app.serv.dto.PostDTO;
import app.serv.model.Post;

import java.util.List;

public interface PostService {

    Post findPostById(Integer id);
    Post createPost(PostDTO postDTO);
    List<Post> findAll();

    List<PostDTO> findAllView();

    void save(Post post);
    void delete(Integer id);

}
