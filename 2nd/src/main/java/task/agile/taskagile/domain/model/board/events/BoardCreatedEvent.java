package task.agile.taskagile.domain.model.board.events;

import lombok.Getter;
import task.agile.taskagile.domain.common.event.DomainEvent;
import task.agile.taskagile.domain.model.board.Board;

import java.util.Objects;

@Getter
public class BoardCreatedEvent extends DomainEvent {

  private static final long serialVersionUID = 1647490808302930238L;

  private Board board;

  public BoardCreatedEvent(Board board) {
    super(board);
    this.board = board;
  }

  @Override
  public String toString() {
    return "BoardCreatedEvent{" +
      "board=" + board +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BoardCreatedEvent that = (BoardCreatedEvent) o;
    return Objects.equals(board, that.board);
  }

  @Override
  public int hashCode() {
    return Objects.hash(board);
  }
}
