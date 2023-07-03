package app.serv.service;

import app.serv.dto.ReactionDTO;
import app.serv.model.Post;

import java.util.Map;
import java.util.Set;

public interface ReactionService {

    ReactionDTO react(ReactionDTO dto, Integer postId);

//    Map<String, Integer> getAllByPost(Post post);

}
