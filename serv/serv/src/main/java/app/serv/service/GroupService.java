package app.serv.service;

import app.serv.dto.GroupDTO;
import app.serv.model.Group;
import app.serv.model.GroupRequest;
import app.serv.model.Post;
import app.serv.model.User;

import java.util.List;
import java.util.Set;

public interface GroupService {

    Group findGroupById(Integer id);
    List<Group> findAll();
    Group createGroup(GroupDTO groupDTO);
    void save(Group group);
    void delete(Integer id);
    Set<User> getAllGroupMembers(Integer id);
    Set<GroupRequest> getAllGroupRequests(Integer id);
    Set<User> getAllGroupAdmins(Integer id);
    Set<Post> getAllGroupPosts(Integer id);
    Set<Group> getAllUserGroups(Integer userId);
    void suspend(Integer groupId, Integer userId, String reason);
    void addAdmin(Integer groupId, Integer userId);


}
