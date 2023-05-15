package app.serv.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@NoArgsConstructor
@Getter
@Setter
public class Administrator extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

}
