package ua.vlasovEugene.springRestTutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserIsUnexistException extends RuntimeException {

  public UserIsUnexistException(String message) {
    super(message);
  }
}
