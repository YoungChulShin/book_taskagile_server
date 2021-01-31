package task.agile.taskagile.domain.model.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import task.agile.taskagile.domain.common.model.AbstractBaseEntity;
import task.agile.taskagile.domain.model.team.Team;
import task.agile.taskagile.domain.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends AbstractBaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "board_id")
  private Long id;

  @Column(name = "name", nullable = false, length = 128, unique = true)
  private String name;

  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  private Team team;

  @Column(name = "archived")
  private boolean archived;

  @OneToMany(mappedBy = "id.board")
  private List<BoardMember> boardMembers = new ArrayList<>();

  //== 생성 메서드
  public static Board create(String name, String description, User user, Team team) {
    Board board = new Board();
    board.name = name;
    board.description = description;
    board.setUser(user);
    board.setTeam(team);

    board.archived = false;

    return board;
  }

  //== 연관관계 메서드
  public void setUser(User user) {
    this.user = user;
    user.getBoards().add(this);
  }

  public void setTeam(Team team) {
    this.team = team;
    if (team != null) {
      team.getBoards().add(this);
    }
  }

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
}
