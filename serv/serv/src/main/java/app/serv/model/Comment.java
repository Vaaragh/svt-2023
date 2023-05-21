//package app.serv.model;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "comments")
//@NoArgsConstructor
//@Getter
//@Setter
//public class Comment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Column(nullable = false)
//    private String text;
//    @Column(nullable = false)
//    private LocalDateTime timestamp;
//    @Column(nullable = false)
//    private Boolean isDeleted;
//    //
//    //    Associations
//    //
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentComment")
//    private Set<Comment> commentList = new HashSet<Comment>();
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "comment_id")
//    private Comment parentComment;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
//    private Set<Reaction> reactionList = new HashSet<Reaction>();
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
//    private Set<Report> reportList = new HashSet<Report>();
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "post_id")
//    private Post post;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//}
