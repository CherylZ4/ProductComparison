package com.example.ProductComparison.service;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.ProductComparison.database.User;
import com.example.ProductComparison.database.UserRepository;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/ingredients")
public class UserController {
    private final UserRepository userRepository;
    private int idCounter = 1; //start with ID 1 and increment for each new user

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        // Manually assign a new ID to the user
        user.setId(idCounter++);

        // Save the user to the database using the UserRepository
        return userRepository.save(user);
    }
}
