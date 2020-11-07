package com.taskagile.domain.model.board;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.taskagile.domain.common.model.AbstractBaseEntity;
import com.taskagile.domain.model.user.User;

import lombok.Getter;

@Getter
@Table(name = "board_member")
@Entity
public class BoardMember extends AbstractBaseEntity {

  private static final long serialVersionUID = 4604919002247993981L;

  @EmbeddedId
  private BoardMemberId id;

  public static BoardMember create(Board board, User user) {
    BoardMember boardMember = new BoardMember();
    boardMember.id = new BoardMemberId(board, user);
    return boardMember;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof BoardMember)) {
      return false;
    }

    BoardMember that = (BoardMember) obj;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "BoardMember{" +
    "id=" + id +
    '}';
  }

  @Embeddable
  private static class BoardMemberId implements Serializable{

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    private BoardMemberId(Board board, User user) {
      this.board = board;
      this.user = user;
    }
  }
}


