package app.serv.model;

import app.serv.enums.ReportReason;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@NoArgsConstructor
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private ReportReason reason;
    @Column(nullable = false)
    private LocalDateTime timestamp;
    @Column(nullable = false)
    private Boolean accepted;
    //
    //    Associations
    //
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "by_user")
    private User byUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "for_user")
    private User forUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment")
    private Comment comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post")
    private Post post;
}
