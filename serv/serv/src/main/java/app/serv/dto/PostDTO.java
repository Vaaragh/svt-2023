package app.serv.dto;

import app.serv.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Integer id;
    @NotBlank
    private String content;

    public PostDTO(Post post){
        this.id = post.getId();
        this.content = post.getContent();
    }

}
