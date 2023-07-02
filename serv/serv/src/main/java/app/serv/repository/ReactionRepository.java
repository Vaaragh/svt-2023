package app.serv.repository;

import app.serv.model.Post;
import app.serv.model.Reaction;
import app.serv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Integer> {

    Optional<Reaction> findByByAndPost(User by, Post post);

}
