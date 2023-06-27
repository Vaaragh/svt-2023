package app.serv.repository;

import app.serv.model.GroupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Repository
public interface GroupRequestRepository extends JpaRepository<GroupRequest, Integer> {
    @Modifying
    @Query(value = "UPDATE GroupRequest set approved=1, at= :approvalDate where id= :groupRequestId")
    void approveGroupRequest(Integer groupRequestId, LocalDateTime approvalDate);

    @Modifying
    @Query(value = "UPDATE GroupRequest set approved=0, at= :denialDate where id= :groupRequestId")
    void declineGroupRequest(Integer groupRequestId, LocalDateTime denialDate);
}