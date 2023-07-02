package app.serv.service.implementation;

import app.serv.dto.GroupDTO;
import app.serv.enums.Role;
import app.serv.model.Group;
import app.serv.model.GroupRequest;
import app.serv.model.Post;
import app.serv.model.User;
import app.serv.repository.GroupRepository;
import app.serv.repository.GroupRequestRepository;
import app.serv.repository.UserRepository;
import app.serv.service.GroupRequestService;
import app.serv.service.GroupService;
import app.serv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private GroupRequestService requestService;

    public Group findGroupById(Integer id){
        Optional<Group> group = groupRepository.findById(id);
        return group.orElse(null);
    }


    @Override
    public Group createGroup(GroupDTO groupDTO) {
        Optional<Group> group = groupRepository.findFirstByName(groupDTO.getName());

        if(group.isPresent()){
            return null;
        }
        Group newGroup = new Group();
        newGroup.setName(groupDTO.getName());
        newGroup.setDescription(groupDTO.getDescription());
        newGroup.setCreationDate(LocalDateTime.now());
        newGroup.setIsSuspended(false);

        Set<User> admins = new HashSet<>();
        admins.add(userService.findLoggedUser());

        newGroup.setGroupAdminList(admins);
        newGroup = groupRepository.save(newGroup);

        GroupRequest newReq = requestService.createForAdmin(userService.findLoggedUser().getId(), newGroup.getId());

        return  newGroup;
    }

    @Override
    public List<Group> findAll() {
        return this.groupRepository.findAll();
    }

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void delete(Integer id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Set<User> getAllGroupMembers(Integer id) {
        return groupRepository.getAllGroupMembers(id);
    }

    @Override
    public Set<GroupRequest> getAllGroupRequests(Integer id) {
        return groupRepository.getAllGroupRequests(id);
    }

    @Override
    public Set<User> getAllGroupAdmins(Integer id) {
        return groupRepository.getAllGroupAdmins(id);
    }

    @Override
    public Set<Post> getAllGroupPosts(Integer id) {
        return groupRepository.getAllGroupPosts(id);
    }

    @Override
    public Set<Group> getAllUserGroups(Integer userId) {
        return groupRepository.getAllUserGroups(userId);
    }

    @Override
    public void suspend(Integer groupId, Integer userId, String reason) {
        groupRepository.suspendGroup(reason, groupId);
    }

    @Override
    public void addAdmin(Integer groupId, Integer userId) {
        Group group = findGroupById(groupId);
        User user = userService.findById(userId);
        group.setGroupAdminList(groupRepository.getAllGroupAdmins(groupId));
        group.getGroupAdminList().add(user);
        save(group);
    }
}
