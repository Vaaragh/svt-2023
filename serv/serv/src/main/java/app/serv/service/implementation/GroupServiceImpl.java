package app.serv.service.implementation;

import app.serv.dto.GroupDTO;
import app.serv.model.Group;
import app.serv.repository.GroupRepository;
import app.serv.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group findGroupById(Integer id){
        Optional<Group> group = groupRepository.findById(id);
        if (!group.isEmpty()){
            return group.get();
        }
        return null;
    }


    @Override
    public Group findGroupByName(String name) {
        Optional<Group> group = groupRepository.findFirstByName(name);
        if (!group.isEmpty()){
            return group.get();
        }
        return null;
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
    public void delete(Integer id) {
        this.groupRepository.deleteById(id);
    }
}
