package spring.intro.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String injectData() {
        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword("12345678");
        userService.add(bob);

        User alice = new User();
        alice.setEmail("alice@gmail.com");
        alice.setPassword("12343210");
        userService.add(alice);

        User john = new User();
        john.setEmail("johndoe@gmail.com");
        john.setPassword("0987654321");
        userService.add(john);

        User jane = new User();
        jane.setEmail("janedoe@gmail.com");
        jane.setPassword("topsecret111");
        userService.add(jane);

        return "4 users were successfully injected";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return castUserToDto(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        List<User> users = userService.listUsers();
        return users.stream()
                .map(this::castUserToDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto castUserToDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
