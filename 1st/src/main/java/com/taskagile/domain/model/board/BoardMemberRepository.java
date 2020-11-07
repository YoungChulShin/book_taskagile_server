package com.taskagile.domain.model.board;

import com.taskagile.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardMemberRepository extends JpaRepository<BoardMember, Long> {

  @Query(" select u " +
         " from User u left join BoardMember bm on u.id = bm.id.user.id " +
         " where bm.id.board.id = :boardId")
  List<User> findMembers(@Param("boardId") Long boardId);
}
