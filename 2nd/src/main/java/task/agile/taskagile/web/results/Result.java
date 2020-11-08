package task.agile.taskagile.web.results;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Result {

  public static ResponseEntity<ApiResult> created() {
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  public static ResponseEntity<ApiResult> created(ApiResult payload) {
    return ResponseEntity.status(HttpStatus.CREATED).body(payload);
  }

  public static ResponseEntity<ApiResult> ok() {
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  public static ResponseEntity<ApiResult> ok(String message) {
    Assert.hasText(message, "Parameter 'message' must not be blank");

    return ok(ApiResult.message(message));
  }

  public static ResponseEntity<ApiResult> ok(ApiResult payload) {
    return ResponseEntity.ok(payload);
  }

  public static ResponseEntity<ApiResult> failure(String message) {
    return ResponseEntity.badRequest().body(ApiResult.message(message));
  }

  public static ResponseEntity<ApiResult> serverError(String message, String errReferenceCode) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult.error(message, errReferenceCode));
  }

  public static ResponseEntity<ApiResult> notFound() {
    return ResponseEntity.notFound().build();
  }

  public static ResponseEntity<ApiResult> unauthenticated() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  public static ResponseEntity<ApiResult> forbidden() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }
}
