package ua.vlasovEugene.springRestTutorial.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
import ua.vlasovEugene.springRestTutorial.exception.UserIsUnexistException;

@RestController
@RequestMapping("vlasov")
public class MainController {

  private final String EXCEPTION_MESSAGE = "User wit this id is not exist";
  private AtomicInteger counter = new AtomicInteger(3);

  private Map<Integer, User> inMemoryDb = Stream.of(new Object[][]{
          {1, new User("John", "Smith")},
          {2, new User("Edward", "Tich")},
          {3, new User("Mike", "Jagger")}
      }
  ).collect(Collectors.toMap(data -> (Integer) data[0], data -> (User) data[1]));

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Integer id) {
    return Optional.of(inMemoryDb.get(id))
        .orElseThrow(() -> new UserIsUnexistException(EXCEPTION_MESSAGE));
  }

  @GetMapping
  public List<User> getAllUsers() {
    return new ArrayList<>(inMemoryDb.values());
  }

  @PostMapping
  public User addNewUser(@RequestBody User newUser) {
    inMemoryDb.put(counter.incrementAndGet(), newUser);
    return newUser;
  }

  @DeleteMapping("/{id}")
  public void deleteUserById(@PathVariable Integer id) {
    if (inMemoryDb.containsKey(id)) {
      inMemoryDb.remove(id);
    } else {
      throw new UserIsUnexistException(EXCEPTION_MESSAGE);
    }
  }

  @PutMapping("/{id}")
  public User updateUserById(@PathVariable Integer id, @RequestBody User userForUpdate) {
    if (inMemoryDb.containsKey(id)) {
      User userFromDb = inMemoryDb.get(id);
      inMemoryDb.replace(id, userForUpdate);
      return userFromDb;
    } else {
      throw new UserIsUnexistException(EXCEPTION_MESSAGE);
    }
  }
}
