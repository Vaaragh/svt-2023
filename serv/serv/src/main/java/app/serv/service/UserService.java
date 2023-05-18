package app.serv.service;

import app.serv.dto.UserDTO;
import app.serv.model.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);
    User createUser(UserDTO userDTO);
    List<User> findAll();


}
