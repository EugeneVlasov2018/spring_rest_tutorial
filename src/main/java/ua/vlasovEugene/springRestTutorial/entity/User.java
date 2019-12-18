package ua.vlasovEugene.springRestTutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private String FIRST_NAME;
  private String LAST_NAME;
}
