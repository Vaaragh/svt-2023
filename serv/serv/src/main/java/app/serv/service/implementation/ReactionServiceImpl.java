package app.serv.service.implementation;

import app.serv.dto.ReactionDTO;
import app.serv.enums.ReactionType;
import app.serv.model.Post;
import app.serv.model.Reaction;
import app.serv.model.User;
import app.serv.repository.ReactionRepository;
import app.serv.service.PostService;
import app.serv.service.ReactionService;
import app.serv.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReactionServiceImpl implements ReactionService {
    ReactionRepository reactionRepository;
    UserService userService;
    PostService postService;

    @Autowired
    public ReactionServiceImpl(ReactionRepository reactionRepository, UserService userService, PostService postService) {
        this.reactionRepository = reactionRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public ReactionDTO react(ReactionDTO dto, Integer postId) {
        User user = userService.findLoggedUser();
        Post post = postService.findPostById(postId);
        Reaction reaction = new Reaction();
        Optional<Reaction> found = reactionRepository.findByByAndPost(user, post);
        if (found.isPresent()) {
            reaction = found.get();
        }

        reaction.setBy(user);
        reaction.setPost(post);
        reaction.setType(ReactionType.valueOf(dto.getReactionType()));
        reaction.setTimestamp(LocalDateTime.now());
        reactionRepository.save(reaction);

        return new ReactionDTO(reaction);
    }
}
