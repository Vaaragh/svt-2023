package app.serv.dto;

import app.serv.model.Reaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReactionDTO {
    private Integer id;
    @NotBlank
    private String reactionType;
    @NotBlank
    private String userUsername;
    private Integer post;
    private LocalDateTime timeStamp;

    public ReactionDTO(Reaction reaction) {
        this.id = reaction.getId();
        this.userUsername = reaction.getBy().getUsername();
        this.reactionType = reaction.getType().toString();
        this.post = reaction.getPost().getId();
        this.timeStamp = reaction.getTimestamp();
    }
}