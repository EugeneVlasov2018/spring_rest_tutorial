package ua.vlasovEugene.springRestTutorial.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.vlasovEugene.springRestTutorial.entity.User;
import ua.vlasovEugene.springRestTutorial.service.UserService;

@RestController
@RequestMapping("vlasov")
@AllArgsConstructor
public class MainController {

  private final UserService service;

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Integer id) {
    return service.getUserById(id);
  }

  @GetMapping
  public List<User> getAllUsers() {
    return service.getAllUsers();
  }

  @PostMapping
  public User addNewUser(@RequestBody User newUser) {
    return service.addNewUser(newUser);
  }

  @DeleteMapping("/{id}")
  public void deleteUserById(@PathVariable Integer id) {
    service.deleteUserById(id);
  }

  @PutMapping("/{id}")
  public User updateUserById(@PathVariable Integer id, @RequestBody User userForUpdate) {
    return service.updateUserById(id, userForUpdate);
  }
}
