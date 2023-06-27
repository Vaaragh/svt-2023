package app.serv.service.implementation;

import app.serv.model.Group;
import app.serv.model.GroupRequest;
import app.serv.model.User;
import app.serv.repository.GroupRequestRepository;
import app.serv.service.GroupRequestService;
import app.serv.service.GroupService;
import app.serv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GroupRequestServiceImpl implements GroupRequestService {

    GroupRequestRepository repo;
    UserService userServ;
    GroupService groupServ;

    public GroupRequestServiceImpl(GroupRequestRepository repo, UserService userServ, GroupService groupServ) {
        this.repo = repo;
        this.userServ = userServ;
        this.groupServ = groupServ;
    }

    @Override
    public GroupRequest create(Integer userId, Integer groupId) {
        Group group = groupServ.findGroupById(groupId);
        User user = userServ.findById(userId);

        GroupRequest req  = new GroupRequest();
        req.setCreated_at(LocalDateTime.now());
        req.setApproved(null);
        req.setAt(null);
        req.setGroup(group);
        req.setUser(user);

        return repo.save(req);
    }

    @Override
    public GroupRequest createForAdmin(Integer userId, Integer groupId) {
        Group group = groupServ.findGroupById(groupId);
        User user = userServ.findById(userId);

        GroupRequest req  = new GroupRequest();
        req.setCreated_at(LocalDateTime.now());
        req.setApproved(true);
        req.setAt(LocalDateTime.now());
        req.setGroup(group);
        req.setUser(user);

        return repo.save(req);
    }

    @Override
    public void approveRequest(Integer requestId, LocalDateTime atDate) {
        repo.approveGroupRequest(requestId,atDate);
    }

    @Override
    public void declineRequest(Integer requestId, LocalDateTime atDate) {
        repo.approveGroupRequest(requestId,atDate);
    }
}
