package pcc.portal.portalback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pcc.portal.portalback.entity.UserEntity;
import pcc.portal.portalback.model.UserModel;
import pcc.portal.portalback.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @PostMapping("/login")
//    public UserModel login(@RequestBody UserModel userModel) {
//        UserModel user = userService.getEmail(userModel.getEmail());
//        if (user != null && user.getPassword().equals(userModel.getPassword())) {
//            return user;
//        }
//        return null;
//    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        Object result = userService.checkEmailPassword(email, password);
        if (result instanceof UserEntity) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/create")
    public UserModel createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

}

