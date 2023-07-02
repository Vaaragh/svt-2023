package app.serv.model;

import app.serv.enums.ReactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reactions")
@NoArgsConstructor
@Getter
@Setter
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private ReactionType type;
    @Column(nullable = false)
    private LocalDateTime timestamp;
    @ManyToOne
    private User by;
    @ManyToOne
    private Post post;
}
