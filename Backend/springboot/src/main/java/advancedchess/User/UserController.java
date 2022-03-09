package advancedchess.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "UserController", description = "REST APIs related to User")
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping("/")
    public String welcome() {
        return "testing";
    }

    @ApiOperation(value = "Get list of all users", response = User.class, tags = "getUsers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @ApiOperation(value = "Get specific user by id", response = User.class, tags = "getUser")
    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }

    @ApiOperation(value = "Get username by id", response = User.class, tags = "getUsername")
    @GetMapping(path = "/users/{id}/username")
    String getUsername(@PathVariable int id){
        return userRepository.findById(id).getUsername();
    }

    @ApiOperation(value = "Get password by id", response = User.class, tags = "getPassword")
    @GetMapping(path = "/users/{id}/password")
    String getUserPassword(@PathVariable int id){
        return userRepository.findById(id).getPassword();
    }

    @ApiOperation(value = "Post a new User", response = User.class, tags = "createUser")
    @GetMapping(path = "/usersName/{username}/password")
    String getUserPassword(@PathVariable String username){
        return userRepository.findByUsername(username).getPassword();
    }
    @ApiOperation(value = "Create a User", response = User.class, tags = "createUser")
    @PostMapping(path = "/users")
    String createUser(@RequestBody User user1){
        if (user1 == null)
            return failure;
        userRepository.save(user1);
        return success;
    }

    @ApiOperation(value = "Update a User", response = User.class, tags = "updateUser")
    @PutMapping("/users/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);
        if(user == null)
            return null;

        userRepository.save(request);
        return userRepository.findById(id);
    }

}
