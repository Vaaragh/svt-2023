package app.serv.repository;

import app.serv.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface PostRepository extends JpaRepository<Post, Integer> {



}
