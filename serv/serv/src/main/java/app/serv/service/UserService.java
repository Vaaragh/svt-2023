package app.serv.service;

import app.serv.dto.PasswordDTO;
import app.serv.dto.UserDTO;
import app.serv.model.User;

import java.util.List;

public interface UserService {

    User findLoggedUser();
    User findByUsername(String username);
    User findById(Integer userId);
    List<User> findAll();
    User createUser(UserDTO userDTO);
    void save(User user);
    UserDTO changePassword(PasswordDTO passwords);
    UserDTO editUser(UserDTO user);



    }
