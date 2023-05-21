//package app.serv.model;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "groupRequests")
//@NoArgsConstructor
//@Getter
//@Setter
//public class GroupRequest {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Column
//    private Boolean approved;
//    @Column
//    private LocalDateTime created_at; // creation date
//    @Column
//    private LocalDateTime at;  // approval/denial date
//    //
//    //    Associations
//    //
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "group_id")
//    private Group group;
//
//}
