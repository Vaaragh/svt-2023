package app.serv.service;

import app.serv.model.GroupRequest;

import java.time.LocalDateTime;

public interface GroupRequestService {
    GroupRequest create(Integer userId, Integer groupId);
    GroupRequest createForAdmin(Integer userId, Integer groupId);
    void approveRequest(Integer requestId, LocalDateTime atDate);
    void declineRequest(Integer requestId, LocalDateTime atDate);
}