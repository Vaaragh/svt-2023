package app.serv.service.implementation;

import app.serv.dto.PasswordDTO;
import app.serv.dto.UserDTO;
import app.serv.enums.Role;
import app.serv.model.User;
import app.serv.repository.UserRepository;
import app.serv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {


    UserRepository userRepository;
    PasswordEncoder encoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findFirstByUsername(username);
        return user.orElse(null);
    }

    @Override
    public User findLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return this.findByUsername(username);
    }

    @Override
    public User findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NullPointerException("User not found with id: " + userId));
    }

    @Override
    public User createUser(UserDTO userDTO) {

        Optional<User> user = userRepository.findFirstByUsername(userDTO.getUsername());

        if(user.isPresent()){
            return null;
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(encoder.encode(userDTO.getPassword()));
        newUser.setRole(Role.USER);
        newUser.setEmail(userDTO.getEmail());
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser = userRepository.save(newUser);

        return newUser;
    }

    @Override
    public UserDTO changePassword(PasswordDTO passwords){
        User createdUser = this.findLoggedUser();
        if (encoder.matches(passwords.getCurrent(), createdUser.getPassword()) && passwords.getConfirm().equals(passwords.getPassword())){
            createdUser.setPassword(encoder.encode(passwords.getPassword()));
            this.save(createdUser);
        } else {
            return null;
        }
        return new UserDTO(createdUser);
    }

    @Override
    public UserDTO editUser(UserDTO user) {
       User oldUser = findLoggedUser();
       oldUser.setUsername(user.getUsername());
       oldUser.setEmail(user.getEmail());
       oldUser.setFirstName(user.getFirstName());
       oldUser.setLastName(user.getLastName());
       this.save(oldUser);
       return user;
    }


    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }
}
