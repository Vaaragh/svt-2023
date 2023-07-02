package app.serv.model;

import app.serv.repository.GroupRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToMany
    @JsonIgnore
    private Set<User> groupAdminList = new HashSet<>();
    @OneToMany
    @JsonIgnore
    private Set<Post> groupPostList = new HashSet<>();
    @OneToMany
    @JsonIgnore
    private Set<GroupRequest> groupRequestList = new HashSet<>();

}
