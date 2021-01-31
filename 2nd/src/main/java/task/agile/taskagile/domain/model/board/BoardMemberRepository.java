package task.agile.taskagile.domain.model.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardMemberRepository extends JpaRepository<BoardMember, BoardMember.BoardMemberId> {
}
