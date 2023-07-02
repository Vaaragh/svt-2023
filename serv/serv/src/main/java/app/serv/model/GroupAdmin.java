package app.serv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "group_admins")
@NoArgsConstructor
@Getter
@Setter
public class GroupAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //
    //    Associations
    //
    @ManyToOne
    private User user;
    @ManyToOne
    private Group group;

}
