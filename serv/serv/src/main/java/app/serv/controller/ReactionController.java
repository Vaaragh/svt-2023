package app.serv.controller;

import app.serv.dto.ReactionDTO;
import app.serv.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("api/reactions")
public class ReactionController {

    ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping("/react")
    public ResponseEntity<ReactionDTO> create(@RequestBody @Validated ReactionDTO dto){
        ReactionDTO reactionDTO = reactionService.react(dto, dto.getPost());
        if (reactionDTO == null) {
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reactionDTO, HttpStatus.CREATED);
    }
}
