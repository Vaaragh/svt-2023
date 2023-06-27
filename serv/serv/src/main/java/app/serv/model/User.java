package app.serv.model;


import app.serv.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column
    private LocalDateTime lastLogin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//    private Set<Post> postList = new HashSet<Post>();
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "image_id", referencedColumnName = "id")
//    private Image image;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private Set<GroupAdmin> groupAdminList = new HashSet<GroupAdmin>();
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentFriend")
//    private Set<User> friendList = new HashSet<User>();
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "friend_id")
//    private User parentFriend;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private Set<Banned> banList = new HashSet<Banned>();
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private Set<Comment> commentList = new HashSet<Comment>();
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "byUser")
//    private Set<FriendRequest> sentRequests = new HashSet<FriendRequest>();
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "forUser")
//    private Set<FriendRequest> receivedRequests = new HashSet<FriendRequest>();
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "byUser")
//    private Set<Report> sentReports = new HashSet<Report>();
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "forUser")
//    private Set<Report> receivedReports = new HashSet<Report>();
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private Set<GroupRequest> groupRequestList = new HashSet<GroupRequest>();
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private Set<Reaction> reactionList = new HashSet<Reaction>();

}
