package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto.NewUserDto;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.login.Login;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User signIn(@RequestBody @Valid NewUserDto anUser){
        return userService.signUp(anUser);
    }

    @PostMapping("/users/sessions")
    public User signIn(@RequestBody @Valid Login anUser){
        return userService.signIn(anUser);
    }
}
