package app.serv.service.implementation;

import app.serv.dto.GroupDTO;
import app.serv.enums.Role;
import app.serv.model.Group;
import app.serv.model.User;
import app.serv.repository.GroupRepository;
import app.serv.repository.GroupRequestRepository;
import app.serv.repository.UserRepository;
import app.serv.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    GroupRepository groupRepository;
    GroupRequestRepository reqRepository;
    UserRepository userRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, GroupRequestRepository reqRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.reqRepository = reqRepository;
        this.userRepository = userRepository;
    }

    public Group findGroupById(Integer id){
        Optional<Group> group = groupRepository.findById(id);
        return group.orElse(null);
    }


    @Override
    public Group findGroupByName(String name) {
        Optional<Group> group = groupRepository.findFirstByName(name);
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
        newGroup = groupRepository.save(newGroup);
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
    public void suspend(Integer groupId, Integer userId, String reason) {
        User user = userRepository.findById(userId).orElse(null);
        if (user !=null){
            if (user.getRole() == Role.ADMIN){
                groupRepository.suspendGroup(reason, groupId);
            }
        }
    }
}
