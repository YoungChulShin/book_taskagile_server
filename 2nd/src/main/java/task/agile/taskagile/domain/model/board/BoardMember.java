package task.agile.taskagile.domain.model.board;

import lombok.Getter;
import task.agile.taskagile.domain.common.model.AbstractBaseEntity;
import task.agile.taskagile.domain.model.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "board_member")
@Getter
public class BoardMember extends AbstractBaseEntity {








  @Override
  public boolean equals(Object obj) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public String toString() {
    return null;
  }

  public static class BoardMemberId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public BoardMemberId(Board board, User user) {

    }
  }
}
