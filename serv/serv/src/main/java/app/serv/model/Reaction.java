//package app.serv.model;
//
//import app.serv.enums.ReactionType;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "reactions")
//@NoArgsConstructor
//@Getter
//@Setter
//public class Reaction {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Column(nullable = false)
//    private ReactionType type;
//    @Column(nullable = false)
//    private LocalDateTime timestamp;
//    //
//    //    Associations
//    //
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "comment_id")
//    private Comment comment;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "post_id")
//    private Post post;
//
//}
