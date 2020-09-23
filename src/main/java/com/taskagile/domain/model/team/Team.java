package com.taskagile.domain.model.team;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.taskagile.domain.common.model.AbstractBaseEntity;
import com.taskagile.domain.model.user.User;

import lombok.Getter;
import org.hibernate.annotations.Type;

@Getter
@Table(name = "team")
@Entity
public class Team extends AbstractBaseEntity {

  private static final long serialVersionUID = -2264390861852998965L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 128)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "archived", columnDefinition = "TINYINT(1)")
  private boolean archived;

  private LocalDateTime createdDate;

  public static Team create(String name, User user) {
    Team team = new Team();
    team.name = name;
    team.user = user;
    team.archived = false;
    team.createdDate = LocalDateTime.now();

    return team;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Team)) {
      return false;
    }

    Team team = (Team)obj;

    return user.getId() == team.getUser().getId() && Objects.equals(name, team.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, user.getId());
  }

  @Override
  public String toString() {
    return "Team{" +
    "id=" + id +
    ", name='" + name + '\'' +
    ", userId=" + user.getId() +
    ", archived=" + archived +
    ", createdDate=" + createdDate +
    '}';
  }
}
