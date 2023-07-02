package app.serv.controller;

import app.serv.dto.PostDTO;
import app.serv.model.Post;
import app.serv.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> create(@RequestBody @Validated PostDTO post){
        Post createdPost = postService.createPost(post);
        return new ResponseEntity<>(new PostDTO(createdPost), HttpStatus.CREATED);
    }

    @DeleteMapping()
    public void delete(@RequestParam Integer id){postService.delete(id);}

    @GetMapping("/all")
    public List<PostDTO> loadAll(){return this.postService.findAllView();}

    @PutMapping("/edit")
    public ResponseEntity<PostDTO> edit(@RequestBody @Validated PostDTO editPost){
        Post edit = postService.findPostById(editPost.getId());
        edit.setContent(editPost.getContent());
        postService.save(edit);

        PostDTO postDTO = new PostDTO(edit);
        return  new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }

}
