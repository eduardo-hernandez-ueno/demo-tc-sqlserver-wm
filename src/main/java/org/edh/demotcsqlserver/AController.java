package org.edh.demotcsqlserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AController {

    private UserRepository userRepository;
    private UsersClient usersClient;

    public AController(UserRepository userRepository, UsersClient usersClient) {
        this.userRepository = userRepository;
        this.usersClient = usersClient;
    }

    @GetMapping( value = "/{id}", produces = { "application/json" })
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        User user = null;
        try {
            user = usersClient.getById(id);
        } catch (RuntimeException e) {
        }
        if (user == null) {
            try {
                user = userRepository.findById(Integer.valueOf(id)).orElse(null);
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("{ \"desc\": \"Bad request\" }");
            }
        }
        return ResponseEntity.ok(user);
    }
}
