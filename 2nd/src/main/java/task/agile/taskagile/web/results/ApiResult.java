package task.agile.taskagile.web.results;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.HashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResult extends HashMap<String, Object> {

  private static final long serialVersionUID = -4571516614115466255L;

  private static final String MESSAGE_KEY = "message";
  private static final String ERROR_CODE_KEY = "errorReferenceCode";

  public static ApiResult blank() {
    return new ApiResult();
  }

  public static ApiResult message(String message) {
    Assert.hasText(message, "Parameter message must not be blank");

    ApiResult apiResult = new ApiResult();
    apiResult.put(MESSAGE_KEY, message);

    return apiResult;
  }

  public static ApiResult error(String message, String errorCodeReference) {
    Assert.hasText(message, "Parameter message must not be blank");
    Assert.hasText(errorCodeReference, "Parameter errorCodeReference must not be blank");

    ApiResult apiResult = new ApiResult();
    apiResult.put(MESSAGE_KEY, message);
    apiResult.put(ERROR_CODE_KEY, errorCodeReference);
    return apiResult;
  }

  public ApiResult add(String key, String value) {
    Assert.hasText(key, "Parameter key must not be blank");
    Assert.hasText(value, "Parameter value must not be blank");

    this.put(key, value);
    return this;
  }
}
