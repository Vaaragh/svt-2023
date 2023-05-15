package app.serv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bans")
@NoArgsConstructor
@Getter
@Setter
public class Banned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private LocalDateTime timestamp;
    //
    //    Associations
    //
    @ManyToOne(fetch = FetchType.EAGER)
    private Administrator admin;
    @ManyToOne(fetch = FetchType.EAGER)
    private GroupAdmin groupAdmin;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;


}
