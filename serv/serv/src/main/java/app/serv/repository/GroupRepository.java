package app.serv.repository;

import app.serv.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findFirstByName(String name);

    @Modifying
    @Query(value = "UPDATE Group SET isSuspended=1, suspendedReason=:reason WHERE id=:id")
    void suspendGroup(String reason, Integer id);

}
