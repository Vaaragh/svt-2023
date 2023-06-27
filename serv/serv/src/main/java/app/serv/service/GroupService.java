package app.serv.service;

import app.serv.dto.GroupDTO;
import app.serv.model.Group;

import java.util.List;

public interface GroupService {

    Group findGroupByName(String name);

    Group findGroupById(Integer id);

    Group createGroup(GroupDTO groupDTO);

    List<Group> findAll();

    void save(Group group);

    void suspend(Integer groupId, Integer userId, String reason);
}
