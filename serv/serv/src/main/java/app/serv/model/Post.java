package app.serv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "posts")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    private User user;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
//    private Set<Comment> commentList = new HashSet<Comment>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
//    private Set<Reaction> reactionList = new HashSet<Reaction>();
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
//    private Set<Report> reportList = new HashSet<Report>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
//    private Set<Image> imageList = new HashSet<Image>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
}
