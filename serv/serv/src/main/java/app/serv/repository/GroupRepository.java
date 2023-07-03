package app.serv.repository;

import app.serv.model.Group;
import app.serv.model.GroupRequest;
import app.serv.model.Post;
import app.serv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findFirstByName(String name);

    @Query(value = "SELECT m FROM GroupRequest g JOIN g.user m WHERE g.approved = true AND g.group.id = :groupId")
    Set<User> getAllGroupMembers(Integer groupId);

    @Query(value = "SELECT a FROM Group g JOIN g.groupAdminList a WHERE g.id = :groupId")
    Set<User> getAllGroupAdmins(Integer groupId);

    @Query(value = "SELECT p FROM Post p WHERE p.group.id = :groupId")
    Set<Post> getAllGroupPosts(Integer groupId);

    @Query("SELECT g FROM GroupRequest gr JOIN gr.group g WHERE gr.approved = true AND gr.user.id = :userId")
    Set<Group> getAllUserGroups(Integer userId);

    @Query(value = "SELECT r FROM Group g JOIN g.groupRequestList r WHERE r.approved = null AND g.id = :groupId")
    Set<GroupRequest> getAllGroupRequests(Integer groupId);


    @Modifying
    @Query(value = "UPDATE Group SET isSuspended=1, suspendedReason=:reason WHERE id=:id")
    void suspendGroup(String reason, Integer id);

}
