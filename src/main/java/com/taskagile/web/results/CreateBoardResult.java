package com.taskagile.web.results;

import com.taskagile.domain.model.board.Board;
import org.springframework.http.ResponseEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateBoardResult {

  public static ResponseEntity<ApiResult> build(Board board) {
    ApiResult apiResult = ApiResult.blank()
      .add("id", board.getId().toString())
      .add("name", board.getName())
      .add("description", board.getDescription())
      .add("teamId", board.getTeam().getId().toString())
      .add("userId", board.getUser().getId().toString());

    return Result.created(apiResult);
  }
}
