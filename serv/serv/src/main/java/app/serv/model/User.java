package app.serv.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


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

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Post> postList = new HashSet<Post>();
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<GroupAdmin> groupAdminList = new HashSet<GroupAdmin>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentFriend")
    private Set<User> friendList = new HashSet<User>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_id")
    private User parentFriend;

}
