package dev.roy.userservice.api;

import dev.roy.userservice.model.Role;
import dev.roy.userservice.model.User;
import dev.roy.userservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>showUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User>saveUser(@RequestBody User user) {
        return ResponseEntity.created(getUri("/api/user/save")).body(userService.saveUser(user));
    }


    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        return ResponseEntity.created(getUri("/api/user/save")).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }

    //Response 201
    private static URI getUri(String ApiPath) {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(ApiPath).toUriString());
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
