package app.serv.repository;

import app.serv.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT p FROM Post p JOIN p.user u WHERE p.user.id = :userId")
    Set<Post> getAllPostsByUsers(Integer userId);

    @Query("SELECT p FROM Post p JOIN p.user WHERE p.isDeleted = false")
    Set<Post> getAllExisting();


}
