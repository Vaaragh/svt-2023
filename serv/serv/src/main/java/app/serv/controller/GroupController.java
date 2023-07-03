package app.serv.controller;

import app.serv.dto.GroupDTO;
import app.serv.dto.PostDTO;
import app.serv.model.Group;
import app.serv.model.GroupRequest;
import app.serv.model.Post;
import app.serv.model.User;
import app.serv.service.GroupRequestService;
import app.serv.service.GroupService;
import app.serv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/groups")
public class GroupController {


    GroupService groupService;
    UserService userService;
    GroupRequestService requestService;

    @Autowired
    public GroupController(GroupService groupService, UserService userService,GroupRequestService requestService) {
        this.groupService = groupService;
        this.userService = userService;
        this.requestService = requestService;
    }

    @PostMapping("/create")
    public ResponseEntity<GroupDTO> create(@RequestBody @Validated GroupDTO newGroup){

        Group createdGroup = groupService.createGroup(newGroup);

        if(createdGroup == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        GroupDTO groupDTO = new GroupDTO(createdGroup);
        return new ResponseEntity<>(groupDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        groupService.delete(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<GroupDTO> edit(@RequestBody @Validated GroupDTO editGroup){
        Group edit = groupService.findGroupById(editGroup.getId());
        edit.setDescription(editGroup.getDescription());
        edit.setName(editGroup.getName());
        groupService.save(edit);

        GroupDTO groupDTO = new GroupDTO(edit);
        return  new ResponseEntity<>(groupDTO, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Group> loadAll(){return this.groupService.findAll();}

    @GetMapping("/userGroups")
    public Set<Group> getAllUserGroups(){
        return this.groupService.getAllUserGroups(this.userService.findLoggedUser().getId());
    }


    @GetMapping("/{groupId}/members")
    public Set<User> getMembers(@PathVariable Integer groupId){
        return groupService.getAllGroupMembers(groupId);
    }

    @PostMapping("/{groupId}/join")
    public ResponseEntity<GroupDTO> joinGroup(@PathVariable Integer groupId){
        requestService.create(userService.findLoggedUser().getId(), groupId);
        return new ResponseEntity<>(new GroupDTO(groupService.findGroupById(groupId)), HttpStatus.CREATED);
    }

    @GetMapping("/{groupId}/admins")
    public Set<User> getAdmins(@PathVariable Integer groupId){
       return groupService.getAllGroupAdmins(groupId);
    }

    @PostMapping("/{groupId}/admins/{userId}")
    public void addAdmin(@PathVariable Integer groupId, @PathVariable Integer userId){
        groupService.addAdmin(groupId, userId);
    }

}
