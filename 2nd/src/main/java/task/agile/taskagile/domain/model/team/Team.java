package task.agile.taskagile.domain.model.team;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import task.agile.taskagile.domain.common.model.AbstractBaseEntity;
import task.agile.taskagile.domain.model.board.Board;
import task.agile.taskagile.domain.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team extends AbstractBaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "team_id")
  private Long id;

  @Column(name = "name", nullable = false, length = 120)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "archived")
  private boolean archived;

  @OneToMany(mappedBy = "team")
  private List<Board> boards = new ArrayList<>();

  //== 생성 메서드
  public static Team create(String name, User user) {
    Team team = new Team();
    team.name = name;
    team.setUser(user);
    team.archived = false;

    return team;
  }

  //== 연관관계 메서드
  public void setUser(User user) {
    this.user = user;
    user.getTeams().add(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Team team = (Team) o;
    return Objects.equals(name, team.name) &&
      Objects.equals(user, team.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, user);
  }

  @Override
  public String toString() {
    return "Team{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", user=" + user +
      ", archived=" + archived +
      ", createdDate=" + createdDate +
      ", modifiedDate=" + modifiedDate +
      '}';
  }
}
