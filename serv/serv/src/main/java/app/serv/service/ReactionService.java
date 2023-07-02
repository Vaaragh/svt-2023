package app.serv.service;

import app.serv.dto.ReactionDTO;

public interface ReactionService {

    ReactionDTO react(ReactionDTO dto, Integer postId);

}
