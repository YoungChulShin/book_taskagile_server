package com.taskagile.domain.model.board;

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
import com.taskagile.domain.model.team.Team;
import com.taskagile.domain.model.user.User;

import lombok.Getter;
import org.hibernate.annotations.Type;

@Getter
@Table(name = "board")
@Entity
public class Board extends AbstractBaseEntity {

  private static final long serialVersionUID = -3245592151168435709L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 128)
  private String name;

  @Column(name = "description", nullable = false, length = 256)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id", nullable = true)
  private Team team;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "archived", columnDefinition = "TINYINT(1)")
  private boolean archived;

  @Column(name = "createdDate", nullable = false)
  private LocalDateTime createdDate;

  public static Board create(String name, String description, User user, Team team) {
    Board board = new Board();
    board.name = name;
    board.description = description;
    board.user = user;
    board.team = team;
    board.archived = false;
    board.createdDate = LocalDateTime.now();

    return board;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Board)) {
      return false;
    }

    Board that = (Board)obj;
    return user.getId() == that.getId() &&
      team.getId() == that.getId() &&
      Objects.equals(name, that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, user.getId(), team.getId());
  }

  @Override
  public String toString() {
    return "Board{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", userId=" + user.getId() +
      ", teamId=" + ((team == null) ? "" : team.getId()) +
      ", archived=" + archived +
      ", createdDate=" + createdDate +
      '}';
  }
}
