package app.serv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "social_groups")
@NoArgsConstructor
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
    private Boolean isSuspended;
    @Column
    private String suspendedReason;
    //
    //    Associations
    //
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
//    private Set<GroupAdmin> groupAdminList = new HashSet<GroupAdmin>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
//    private Set<Banned> banList = new HashSet<Banned>();
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
//    private Set<GroupRequest> groupRequestList = new HashSet<GroupRequest>();
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
//    private Set<Post> postList = new HashSet<Post>();

}
