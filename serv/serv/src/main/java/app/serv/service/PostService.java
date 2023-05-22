package app.serv.service;

import app.serv.dto.PostDTO;
import app.serv.model.Post;

import java.util.List;

public interface PostService {

    Post findPostByContent(String content);
    Post findPostById(Integer id);
    Post createPost(PostDTO postDTO);
    List<Post> findAll();

    void save(Post post);
    void delete(Integer id);

}
