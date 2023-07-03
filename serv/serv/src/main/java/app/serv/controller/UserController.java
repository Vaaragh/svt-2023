package app.serv.controller;

import app.serv.dto.JwtAuthenticationRequest;
import app.serv.dto.PasswordDTO;
import app.serv.dto.UserDTO;
import app.serv.dto.UserTokenState;
import app.serv.model.Group;
import app.serv.model.User;
import app.serv.security.TokenUtils;
import app.serv.service.GroupService;
import app.serv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class UserController {
    UserService userService;
    GroupService groupService;
    UserDetailsService userDetailsService;
    AuthenticationManager authenticationManager;
    TokenUtils tokenUtils;
    PasswordEncoder encoder;

    @Autowired
    public UserController(GroupService groupService, UserService userService, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, TokenUtils tokenUtils, PasswordEncoder encoder) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
        this.encoder = encoder;
        this.groupService = groupService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> create(@RequestBody @Validated UserDTO newUser){
        User createdUser = userService.createUser(newUser);
        if(createdUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO userDTO = new UserDTO(createdUser);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        User userLogin = userService.findByUsername(user.getUsername());
        userLogin.setLastLogin(LocalDateTime.now());
        userService.save(userLogin);
        String jwt = tokenUtils.generateToken(user);
        int expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }
    @GetMapping("/whoami")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public User user(Principal user) {
        return this.userService.findByUsername(user.getName());
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<User> loadAll() {
        return this.userService.findAll();
    }


    @PutMapping("/password-change")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserDTO> changePassword(@RequestBody @Validated PasswordDTO passwords) {
        UserDTO userDTO = userService.changePassword(passwords);
        if (userDTO == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserDTO> showUser(){
        UserDTO userDTO = new UserDTO(userService.findLoggedUser());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/profile/groups")
    public Set<Group> getUserGroups(){
        return groupService.getAllUserGroups(userService.findLoggedUser().getId());
    }



    @PutMapping("/profile/edit")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserTokenState> editUser(@RequestBody @Validated UserDTO user) {
        UserDTO userDTO = userService.editUser(user);
        if (userDTO == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return createAuthenticationToken(new JwtAuthenticationRequest(userDTO.getUsername(), user.getPassword()), null);
    }


}
