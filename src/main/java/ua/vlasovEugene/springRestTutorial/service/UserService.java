package ua.vlasovEugene.springRestTutorial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;
import ua.vlasovEugene.springRestTutorial.entity.User;
import ua.vlasovEugene.springRestTutorial.exception.UserIsUnexistException;

@Component
public class UserService {

  private final String EXCEPTION_MESSAGE = "User wit this id is not exist";
  private AtomicInteger counter = new AtomicInteger(3);

  private Map<Integer, User> inMemoryDb = Stream.of(new Object[][]{
          {1, new User("John", "Smith")},
          {2, new User("Edward", "Tich")},
          {3, new User("Mike", "Jagger")}
      }
  ).collect(Collectors.toMap(data -> (Integer) data[0], data -> (User) data[1]));

  public User getUserById(Integer id) {
    return Optional.of(inMemoryDb.get(id))
        .orElseThrow(() -> new UserIsUnexistException(EXCEPTION_MESSAGE));
  }

  public List<User> getAllUsers() {
    return new ArrayList<>(inMemoryDb.values());
  }

  public User addNewUser(User newUser) {
    inMemoryDb.put(counter.incrementAndGet(), newUser);
    return newUser;
  }

  public void deleteUserById(Integer id) {
    if (inMemoryDb.containsKey(id)) {
      inMemoryDb.remove(id);
    } else {
      throw new UserIsUnexistException(EXCEPTION_MESSAGE);
    }
  }

  public User updateUserById(Integer id, User userForUpdate) {
    if (inMemoryDb.containsKey(id)) {
      User userFromDb = inMemoryDb.get(id);
      inMemoryDb.replace(id, userForUpdate);
      return userFromDb;
    } else {
      throw new UserIsUnexistException(EXCEPTION_MESSAGE);
    }
  }
}
