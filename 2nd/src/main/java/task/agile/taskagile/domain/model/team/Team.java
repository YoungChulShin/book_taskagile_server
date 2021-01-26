package task.agile.taskagile.domain.model.team;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import task.agile.taskagile.domain.common.model.AbstractBaseEntity;
import task.agile.taskagile.domain.model.user.User;

import javax.persistence.*;

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
