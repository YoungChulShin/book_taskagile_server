package com.taskagile.domain.model.user;

import com.taskagile.domain.common.model.AbstractBaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
public class User extends AbstractBaseEntity {

  private static final long serialVersionUID = -6308226402403647559L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false, length = 50, unique = true)
  private String username;

  @Column(name = "email_address", nullable = false, length = 100, unique = true)
  private String emailAddress;

  @Column(name = "password", nullable = false, length = 30)
  private String password;

  @Column(name = "first_name", nullable = false, length = 45)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 45)
  private String lastName;

  private LocalDateTime createdDate;

  public static User create(String username, String emailAddress, String password) {
    User user = new User();
    user.username = username;
    user.emailAddress = emailAddress;
    user.password = password;
    user.firstName = "";
    user.lastName = "";
    user.createdDate = LocalDateTime.now();
    return user;
  }

//  public UserId getId() {
//    return new UserId(id);
//  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return  true;
    }

    if (!(obj instanceof User)) {
      return false;
    }

    User user = (User)obj;
    return Objects.equals(username, user.getUsername()) &&
      Objects.equals(emailAddress, user.getEmailAddress());
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, emailAddress);
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", emailAddress='" + emailAddress + '\'' +
      ", password=<Protected>" +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", createdDate=" + createdDate +
      '}';
  }
}
